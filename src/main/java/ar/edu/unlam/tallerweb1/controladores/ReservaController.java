package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.ReservaService;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ReservaController {

    @Inject
    private RestaurantService restaurantService;

    @Inject
    private ReservaService reservaService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(path = "/reservar", method = RequestMethod.GET)
    public ModelAndView crearReserva(@RequestParam(value = "restaurant") Long restaurant_id){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();
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
        }catch (Exception e){
            System.err.print(e);
            model.put("error", "No se ha encontrado el restaurant solicitado");
        }
        return new ModelAndView("reservar", model);
    }

    @RequestMapping(path = "/reservar", method = RequestMethod.POST)
    public ModelAndView reservar(@ModelAttribute("reserva") Reserva reserva,
                                 @RequestParam("id_restaurant") Long idR){
        try{
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(idR);
            reserva.setRestaurant(restaurant);
            reservaService.registrarReserva(reserva);
        }catch (Exception e){
            System.err.print("ERROR: " + e);
        }
        return new ModelAndView("redirect:/index");
    }

}
