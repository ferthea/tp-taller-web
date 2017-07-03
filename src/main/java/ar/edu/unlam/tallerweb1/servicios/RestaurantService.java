package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> obtenerListaDeRestaurants();
    List<Restaurant> obtenerListaDeRestaurantsPorNombre(String nombre);
    List<Restaurant> obtenerListaDeRestaurantsPorCategoria(String tipo);
    Restaurant obtenerRestaurantPorId(Long id) throws Exception;
    Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception;
    Boolean usuarioEsDuenioDeUnRestaurant(User usuario, Restaurant restaurant);
    Boolean usuearioEsDuenioDeUnRestaurant(User usuario, Long idRestaurant);
    void actualizarRestaurant(Restaurant restaurant);
    void agregarMenuAUnRestaurant(Long id, Menu menu);
    Menu obtenerMenuPorId(Long id);
}
