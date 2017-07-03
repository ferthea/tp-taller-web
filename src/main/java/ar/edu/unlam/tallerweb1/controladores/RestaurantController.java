package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.enums.IngredientesEnum;
import ar.edu.unlam.tallerweb1.enums.TipoDeRestaurant;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import ar.edu.unlam.tallerweb1.modelo.wrapper.PedidoHelper;
import ar.edu.unlam.tallerweb1.modelo.wrapper.PedidoListWrapper;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import ar.edu.unlam.tallerweb1.servicios.UserService;
import ar.edu.unlam.tallerweb1.servicios.validators.MenuValidator;
import ar.edu.unlam.tallerweb1.servicios.validators.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
import java.util.List;

@Controller
public class RestaurantController {

    @Inject
    private RestaurantService restaurantService;

    @Inject
    private UserService userService;

    @Inject
    private RestaurantValidator restaurantValidator;

    @Inject
    private MenuValidator menuValidator;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/restaurants/{idRestaurant}", method = RequestMethod.GET)
    public ModelAndView getRestaurant(@PathVariable("idRestaurant") Long id){
        ModelMap model = new ModelMap();

        User user = (User)request.getSession().getAttribute("user");

        try{
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(id);
            PedidoListWrapper pedidoListWrapper = new PedidoListWrapper();

            for(Menu menu : restaurant.getListaDeMenues()){
                PedidoHelper pe = new PedidoHelper();
                pe.setIdmenu(menu.getId());
                pe.setMenu(menu);
                pedidoListWrapper.agregarPedido(pe);
            }

            String tipo = "";
            if(user != null && restaurantService.usuarioEsDuenioDeUnRestaurant(user, restaurant)) tipo = "duenio";
            if(user == null || user.getTipo().equals("cliente")) tipo = "cliente";

            model.put("tipo", tipo);
            model.put("restaurant", restaurant);
            model.put("pedidoListWrapper", pedidoListWrapper);
        }catch(Exception e){
            System.err.print(e.getStackTrace());
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

        if(!user.getTipo().equals("restaurant")){
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
    public ModelAndView agregarRestaurant(@ModelAttribute("restaurant") Restaurant restaurant,
                                          @RequestParam("foto_restaurant") MultipartFile foto){
        ModelMap model = new ModelMap();
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) return new ModelAndView("not_logged_page");

        ValidatorResult resultado = restaurantValidator.validarDatosDelRestaurant(restaurant, foto);

        if(resultado.getResultado()){
            try{
                String fotoExtension = foto.getOriginalFilename().split("\\.")[1];
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
        model.put("errores", resultado.getErrores());
        model.put("tiposDeRestaurant", TipoDeRestaurant.values());
        return new ModelAndView("nuevorestaurant", model);
    }

    @RequestMapping(value = "/editar_restaurant", method = RequestMethod.GET)
    public ModelAndView editarRestaurant(@RequestParam("idRestaurant") Long id){
        ModelMap model = new ModelMap();

        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !restaurantService.usuarioEsDuenioDeUnRestaurant(user, id)){
            return new ModelAndView("redirect:/index");
        }

        try{
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(id);
            request.getSession().setAttribute("restaurant_menu", restaurant.getListaDeMenues());
            model.put("restaurant", restaurant);
            model.put("tiposDeRestaurant", TipoDeRestaurant.values());
        }catch(Exception e){
            return new ModelAndView("redirect:/index");
        }
        return new ModelAndView("editar_restaurant", model);
    }

    @RequestMapping(value = "/editar_restaurant", method = RequestMethod.POST)
    public ModelAndView editarRestaurante(@ModelAttribute("restaurant") Restaurant restaurant,
                                          @RequestParam("id") Long id,
                                          @RequestParam("foto_restaurant") MultipartFile foto){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !restaurantService.usuarioEsDuenioDeUnRestaurant(user, id)){
            return new ModelAndView("redirect:/index");
        }

        ValidatorResult resultado = restaurantValidator.validarDatosDelRestaurant(restaurant, foto);
        if(resultado.getResultado()){
            String fotoExtension = foto.getOriginalFilename().split("\\.")[1];
            String path = request.getSession().getServletContext().getRealPath("/images/restaurants/");
            String newName = Integer.toString(foto.hashCode()) + "_" + Integer.toString(new Date().hashCode()) + "." + fotoExtension;
            File nuevaFoto = new File(path+newName);
            try{
                foto.transferTo(nuevaFoto);
            }catch(Exception e){
                System.err.print(e.getStackTrace());
                ModelMap model = new ModelMap();
                model.put("error", "Ha ocurrido un error procesando la foto");
                return new ModelAndView("error_page", model);
            }
            restaurant.setListaDeMenues((List<Menu>) request.getSession().getAttribute("restaurant_menu"));
            restaurant.setFoto(newName);
            restaurantService.actualizarRestaurant(restaurant);
            return new ModelAndView("redirect:/misrestaurantes");
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/agregar_menu", method = RequestMethod.GET)
    public ModelAndView agregarMenu(@RequestParam("idRestaurant") Long id){
        ModelMap model = new ModelMap();
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !restaurantService.usuarioEsDuenioDeUnRestaurant(user, id)){
            return new ModelAndView("redirect:/index");
        }

        Menu menu = new Menu();
        model.put("menu", menu);
        model.put("ingredientes", IngredientesEnum.values());
        model.put("id_restaurant", id);
        return new ModelAndView("agregar_menu", model);
    }

    @RequestMapping(value = "/agregar_menu", method = RequestMethod.POST)
    public ModelAndView agregarNuevoMenu(@RequestParam("idRestaurant") Long id,
                                         @ModelAttribute("menu") Menu menu,
                                         @RequestParam("c_ingredientes") String[] ingredientes){
        ModelMap model = new ModelMap();
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !restaurantService.usuarioEsDuenioDeUnRestaurant(user, id)){
            return new ModelAndView("redirect:/index");
        }

        ValidatorResult resultado = menuValidator.validarMenu(menu);
        if(resultado.getResultado()){
            for(String ingrediente : ingredientes){
                menu.agregarIngrediente(ingrediente);
            }

            restaurantService.agregarMenuAUnRestaurant(id, menu);
            return new ModelAndView("redirect:/restaurants/" + id);
        }

        model.put("menu", menu);
        model.put("errores", resultado.getErrores());
        return new ModelAndView("/agregar_menu?idRestaurant=" + id);

    }

}
