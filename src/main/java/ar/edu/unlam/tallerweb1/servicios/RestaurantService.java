package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> obtenerListaDeRestaurants();
    void agregarNuevoRestaurant(Restaurant restaurant);
}
