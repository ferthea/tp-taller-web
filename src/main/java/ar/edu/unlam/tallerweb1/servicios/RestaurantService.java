package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> obtenerListaDeRestaurants();
    Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception;
    void agregarNuevoRestaurant(Restaurant restaurant);
}
