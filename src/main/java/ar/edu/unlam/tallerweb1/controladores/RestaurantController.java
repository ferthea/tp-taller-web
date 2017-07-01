package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.enums.TipoDeRestaurant;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import ar.edu.unlam.tallerweb1.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class RestaurantController {

    @Inject
    private RestaurantService restaurantService;

    @Inject
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/restaurants/{idRestaurant}", method = RequestMethod.GET)
    public ModelAndView getRestaurant(@PathVariable("idRestaurant") Long id){
        ModelMap model = new ModelMap();

        User user = (User)request.getSession().getAttribute("user");

        try{
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(id);
            String tipo = "";
            if(user != null && restaurantService.usuarioEsDuenioDeUnRestaurant(user, restaurant)) tipo = "duenio";
            if(user == null || user.getTipo().equals("cliente")) tipo = "cliente";

            model.put("tipo", tipo);
            model.put("restaurant", restaurant);
        }catch(Exception e){
            System.err.print(e);
            model.put("error", "No se ha encontrado el restaurant solicitado");
        }
        return new ModelAndView("restaurant", model);
    }

    @RequestMapping(value = "/misrestaurantes", method = RequestMethod.GET)
    public ModelAndView getMisRestaurants(HttpServletRequest request){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();

        User user = (User) request.getSession().getAttribute("user");

        if(user == null) return new ModelAndView("not_logged_page");

        if(!user.getTipo().equals("restaurant")){
            errores.add("No tienes permiso para realizar esta acción");
            model.put("errores", errores);
            return new ModelAndView("error_page", model);
        }

        List<Restaurant> restaurantsDelUser = userService.obtenerListaDeRestaurantsDeUnUsuario(user);
        model.put("restaurants", restaurantsDelUser);

        return new ModelAndView("misrestaurantes", model);
    }

    @RequestMapping(value = "/nuevorestaurant", method = RequestMethod.GET)
    public ModelAndView nuevoRestaurant(HttpServletRequest request){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();

        User user = (User) request.getSession().getAttribute("user");

        if(user == null) return new ModelAndView("not_logged_page");

        if(user.getTipo() != "restaurant"){
            errores.add("No tienes permiso para realizar esta acción");
            model.put("errores", errores);
            return new ModelAndView("error_page", model);
        }

        Restaurant nuevoRestaurant = new Restaurant();
        model.put("restaurant", nuevoRestaurant);
        model.put("tiposDeRestaurant", TipoDeRestaurant.values());

        return new ModelAndView("nuevorestaurant", model);
    }

    @RequestMapping(value = "/nuevorestaurant", method = RequestMethod.POST)
    public ModelAndView agregarRestaurant(HttpServletRequest request,
                                          @ModelAttribute("restaurant") Restaurant restaurant,
                                          @RequestParam("foto_restaurant") MultipartFile foto){
        ModelMap model = new ModelMap();
        List<String> errores = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");
        String[] extensiones = {"jpg", "jpeg", "png"};
        String fotoExtension = "";

        if(user == null) return new ModelAndView("not_logged_page");

        if(restaurant.getNombre().length() < 4){
            errores.add("El nombre debe tener al menos 4 caracteres");
        }

        if(restaurant.getDireccion().length() < 5){
            errores.add("La direccion debe tener al menos 5 caracteres");
        }

        if(restaurant.getTipo() == null || restaurant.getTipo().length() < 4){
            errores.add("Debe seleccionar una categoría");
        }

        if(restaurant.getMaximaCantidadDeClientes() == null){
            errores.add("Ingrese una capacidad valida");
        }

        if(foto.isEmpty()){
            errores.add("Debe ingresar una foto de su restaurant");
        }else{
             fotoExtension = foto.getOriginalFilename().split("\\.")[1];
        }

        if(!Arrays.asList(extensiones).contains(fotoExtension)){
            errores.add("Solamente se aceptan fotos con extencón .jpg, .jpeg o .png");
        }

        if(errores.size() == 0){
            try{
                String path = request.getSession().getServletContext().getRealPath("/images/restaurants/");
                String newName = Integer.toString(foto.hashCode()) + "_" + Integer.toString(new Date().hashCode()) + "." + fotoExtension;
                File nuevaFoto = new File(path+newName);
                foto.transferTo(nuevaFoto);

                restaurant.setFoto(newName);
                userService.agregarRestaurantAUnUsuario(user, restaurant);
                return new ModelAndView("redirect:/misrestaurantes");
            }catch(IllegalStateException e){
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        model.put("errores", errores);
        return new ModelAndView("nuevorestaurant", model);
    }

}
