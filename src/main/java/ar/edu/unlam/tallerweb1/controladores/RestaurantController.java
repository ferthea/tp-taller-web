package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantController {

    @Inject
    private RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants/{idRestaurant}", method = RequestMethod.GET)
    public ModelAndView getRestaurant(@PathVariable("idRestaurant") Long id){
        ModelMap model = new ModelMap();

        try{
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(id);
            model.put("restaurant", restaurant);
        }catch(Exception e){
            model.put("error", "No se ha encontrado el restaurant solicitado");
        }
        return new ModelAndView("restaurant", model);
    }

    @RequestMapping(value = "/misrestaurantes", method = RequestMethod.GET)
    public ModelAndView getMisRestaurants(HttpServletRequest request){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();

        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            User userModel = new User();
            errores.add("Debes estar logeado para realizar esta acción");
            model.put("errores", errores);
            model.put("user", userModel);
            return new ModelAndView("login", model);
        }
        List<Restaurant> restaurantsDelUser = user.getListaDeRestaurantes();
        model.put("restaurants", restaurantsDelUser);

        return new ModelAndView("misrestaurantes", model);
    }

}
