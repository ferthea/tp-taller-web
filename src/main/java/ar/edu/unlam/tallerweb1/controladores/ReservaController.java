package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.exceptions.NoTableAvailableException;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import ar.edu.unlam.tallerweb1.modelo.wrapper.PedidoHelper;
import ar.edu.unlam.tallerweb1.modelo.wrapper.PedidoListWrapper;
import ar.edu.unlam.tallerweb1.servicios.ReservaService;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import ar.edu.unlam.tallerweb1.servicios.validators.ReservaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ReservaController {

    @Inject
    private RestaurantService restaurantService;

    @Inject
    private ReservaService reservaService;

    @Inject
    private ReservaValidator reservaValidator;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(path = "/reservar", method = RequestMethod.POST)
    public ModelAndView crearReserva(@RequestParam(value = "restaurant") Long restaurant_id,
                                     @ModelAttribute("pedidoListWrapper") PedidoListWrapper pedidoListWrapper,
                                     Model modelo){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();

        // Errores traidos por el redirect (errores en la validacion)
        if(modelo.asMap().size() != 0 && modelo.asMap().get("errores_redirect") != null){
            List<String> prueba = (List<String>) modelo.asMap().get("errores_redirect");
            for(String error : prueba){
                errores.add(error);
            }
        }

        User user = (User) request.getSession().getAttribute("user");

        if(user == null) return new ModelAndView("not_logged_page");
        if(user.getTipo().equals("restaurant")){
            errores.add("No tienes permiso para realizar esta acción");
            model.put("errores", errores);
            return new ModelAndView("error_page", model);
        }

        try{
            Reserva reserva = new Reserva();
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(restaurant_id);
            reserva.setUser(user);
            reserva.setRestaurant(restaurant);

            List<Pedido> listaDePedidos = new ArrayList<>();
            for(PedidoHelper pedido : pedidoListWrapper.getPedidosList()){
                if(pedido.getCantidad() > 0){
                    Pedido pe = new Pedido();
                    pe.setMenu(restaurantService.obtenerMenuPorId(pedido.getIdmenu()));
                    pe.setCantidad(pedido.getCantidad());
                    listaDePedidos.add(pe);
                }
            }
            reserva.setPedido(listaDePedidos);

            request.getSession().setAttribute("reserva", reserva);

            model.put("reserva", new Reserva());
            model.put("restaurant", restaurant);
            model.put("errores", errores);
        }catch (Exception e){
            System.err.print(e);
            model.put("error", "No se ha encontrado el restaurant solicitado");
        }
        return new ModelAndView("reservar", model);
    }

    @RequestMapping(path = "/confirmar_reserva", method = RequestMethod.POST)
    public ModelAndView confirmar_reserva(@RequestParam("cantidad_comensales") Integer cantidad_comensales,
                                          @RequestParam("observaciones") String observaciones,
                                          @RequestParam("nueva_fecha") Long fecha,
                                          RedirectAttributes redir) {
        ModelMap model = new ModelMap();
        Double total = 0.0;
        Reserva reserva = (Reserva) request.getSession().getAttribute("reserva");
        reserva.setCantidadComensales(cantidad_comensales);
        reserva.setObservaciones(observaciones);
        reserva.setFecha(new Date(fecha));

        ValidatorResult resultado =
                reservaValidator.validarCantidadDeComensales(reserva.getRestaurant().getId(),
                        fecha, reserva.getCantidadComensales());

        if (!resultado.getResultado()) {
            redir.addFlashAttribute("errores_redirect", resultado.getErrores());
            return new ModelAndView("redirect:/reservar?restaurant=" + reserva.getRestaurant().getId());
        }

        for(Pedido pedido : reserva.getPedidos()){
            total += pedido.getCantidad() * pedido.getMenu().getPrecio();
        }
        request.getSession().setAttribute("reserva", reserva);
        model.put("reserva", reserva);
        model.put("total", total);
        return  new ModelAndView("confirmar_reserva", model);

    }

    @RequestMapping(path = "/crear_reserva", method = RequestMethod.POST)
    public ModelAndView crear_reserva(){
        ModelMap model = new ModelMap();
        Reserva reserva = (Reserva) request.getSession().getAttribute("reserva");
        List<String> errores = new ArrayList<>();

        try{
            reservaService.registrarReserva(reserva);
        }catch(NoTableAvailableException e){
            errores.add("No hay lugar disponible para la fecha solicitada");
            return new ModelAndView("error_page");
        }catch (Exception e){
            System.err.print(e.getStackTrace());
            errores.add("Ha ocurrido un error. Por favor, intentelo mas tarde");
            return new ModelAndView("error_page");
        }

        model.put("reserva", reserva);
        return new ModelAndView("reserva_exito", model);
    }

    @RequestMapping(path = "/cancelar_reserva", method = RequestMethod.GET)
    public ModelAndView cancelar_reserva(@RequestParam("reserva") Long id){
        ValidatorResult resultado = reservaValidator.validarReserva(id);
        if(!resultado.getResultado()){
            ModelMap model = new ModelMap();
            model.put("errores", resultado.getErrores());
            return new ModelAndView("error_page", model);
        }
        reservaService.eliminarReserva(id);
        return new ModelAndView("redirect:/misreservas");
    }

}
