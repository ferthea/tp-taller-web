package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;

import java.util.List;

public interface RestaurantDao {
    List<Restaurant> obtenerRestaurants();
    Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception;
    Restaurant obtenerRestaurantPorId(Long id) throws Exception;
    List<Restaurant> obtenerListaDeRestaurantsPorNombre(String nombre);
    List<Restaurant> obtenerListaDeRestaurantsPorCategoria(String categoria);
    List<String> obtenerListaDeCategorias();
}
