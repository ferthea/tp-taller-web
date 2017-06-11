package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Inject
    private RestaurantService restaurantService;

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(value = "q", required = false) String query,
                               @RequestParam(value = "category", required = false) String category){
        ModelMap model = new ModelMap();

        List <Restaurant> listaDeRestaurants =
                query != null ?
                        restaurantService.obtenerListaDeRestaurantsPorNombre(query) :
                        category != null ?
                                restaurantService.obtenerListaDeRestaurantsPorCategoria(category) : new ArrayList<Restaurant>();

        if(listaDeRestaurants.size() == 0){
            model.put("error", "No se han encontrado resultados.");
        }else{
            model.put("restaurants", listaDeRestaurants);
        }
        return new ModelAndView("search", model);
    }
}
