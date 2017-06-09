package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;

import java.util.List;

public interface RestaurantDao {
    List<Restaurant> obtenerRestaurants();
    void agregarRestaurant(Restaurant restaurant);
    Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception;
    Restaurant obtenerRestaurantPorId(Integer id) throws Exception;
    List<Restaurant> obtenerListaDeRestaurantsPorNombre(String nombre);
}
