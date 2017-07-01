package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> obtenerListaDeRestaurants();
    List<Restaurant> obtenerListaDeRestaurantsPorNombre(String nombre);
    List<Restaurant> obtenerListaDeRestaurantsPorCategoria(String tipo);
    List<String> obtenerListaDeCategorias();
    Restaurant obtenerRestaurantPorId(Long id) throws Exception;
    Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception;
    void agregarNuevoRestaurant(Restaurant restaurant);
    Boolean usuarioEsDuenioDeUnRestaurant(User usuario, Restaurant restaurant);
}
