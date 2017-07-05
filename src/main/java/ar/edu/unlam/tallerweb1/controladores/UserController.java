package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.ReservaService;
import ar.edu.unlam.tallerweb1.servicios.RestaurantService;
import ar.edu.unlam.tallerweb1.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Inject
    private ReservaService reservaService;

    @Inject
    private UserService userService;

    @Inject
    private RestaurantService restaurantService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/misreservas", method = RequestMethod.GET)
    public ModelAndView mostrar_reservas(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "restaurant", required = false) Integer restaurant){
        ModelMap model = new ModelMap();
        final Integer PAGE_SIZE = 10;
        User user = (User) request.getSession().getAttribute("user");

        if(user == null) return new ModelAndView("redirect:/index");

        Integer nPagina = page == null ? 0 : page;

        List<Reserva> reservas = new ArrayList<>();
        Integer cantidad_a_paginar = 0;

        if(user.getTipo().equals("cliente")){
            reservas = reservaService.obtenerReservasDeUnUserPaginadas(user.getId(), nPagina);
            cantidad_a_paginar = reservaService.obtenerCantidadDeRerservasDeUnUser(user.getId()).intValue() / PAGE_SIZE;
        }else{
            if(restaurant == null){
                model.put("restaurants", userService.obtenerListaDeRestaurantsDeUnUsuario(user));
            }else{
                if(!restaurantService.usuarioEsDuenioDeUnRestaurant(user, restaurant.longValue()))
                    return new ModelAndView("redirect:/index");
                reservas = reservaService.obtenerReservasDeUnRestaurantPaginadas(restaurant.longValue(), nPagina);
                cantidad_a_paginar = reservaService.obtenerCantidadDeReservasDeUnRestaurant(restaurant.longValue()).intValue() / PAGE_SIZE;
            }
        }
        model.put("reservas", reservas);
        model.put("cantidad_a_paginar", cantidad_a_paginar - 1);
        return new ModelAndView("misreservas", model);
    }
}
