package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import ar.edu.unlam.tallerweb1.servicios.ReservaService;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import ar.edu.unlam.tallerweb1.servicios.validators.ReservaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @RequestMapping(path = "/reservar", method = RequestMethod.GET)
    public ModelAndView crearReserva(@RequestParam(value = "restaurant") Long restaurant_id,
                                     Model modelo){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();

        // Errores traidos por el redirect (errores en la validacion)
        if(modelo.asMap().size() != 0){
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

            model.put("reserva", reserva);
            model.put("restaurant", restaurant);
            model.put("errores", errores);
        }catch (Exception e){
            System.err.print(e);
            model.put("error", "No se ha encontrado el restaurant solicitado");
        }
        return new ModelAndView("reservar", model);
    }

    @RequestMapping(path = "/reservar", method = RequestMethod.POST)
    public ModelAndView reservar(@RequestParam(value = "restaurant_id", required = true) Long idR,
                                 @RequestParam(value = "nueva_fecha") Long fecha,
                                 @RequestParam(value = "cantidad_comensales") Integer cantidad_comensales,
                                 RedirectAttributes redir){

        try{
            ValidatorResult resultado = reservaValidator.validarCantidadDeComensales(idR, fecha, cantidad_comensales);
            if(!resultado.getResultado()){
                redir.addFlashAttribute("errores_redirect", resultado.getErrores());
                return new ModelAndView("redirect:/reservar?restaurant=" + idR);
            }
            ModelMap model = new ModelMap();
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(idR);
            model.put("menues", restaurant.getListaDeMenues());
            return new ModelAndView("/seleccionar_menu", model);
        }catch (Exception e){
            System.err.print("ERROR: " + e);
        }
        return new ModelAndView("redirect:/index");
    }

}
