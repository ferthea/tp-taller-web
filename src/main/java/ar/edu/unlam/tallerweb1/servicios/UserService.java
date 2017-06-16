package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;

import java.util.List;

/**
 * Created by fthea on 6/15/17.
 */
public interface UserService {
    void agregarRestaurantAUnUsuario(User user, Restaurant restaurant);
    List<Restaurant> obtenerListaDeRestaurantsDeUnUsuario(User user);
}
