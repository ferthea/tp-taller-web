package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
public class RestaurantController {

    @Inject
    private RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants/{nombreDelRestaurant}", method = RequestMethod.GET)
    public ModelAndView getRestaurant(@PathVariable("nombreDelRestaurant") String nombre){
        //en la vista muestro en enlace reemplazando los espacios por '_', aca los vuelvo a transformar en espacios
        nombre = nombre.replace("_", " ");

        ModelMap model = new ModelMap();

        try{
            Restaurant restaurant = restaurantService.obtenerRestaurantPorNombre(nombre);
            model.put("restaurant", restaurant);
        }catch(Exception e){
            model.put("error", "No se ha encontrado el restaurant solicitado");
        }
        return new ModelAndView("restaurant", model);
    }

}
