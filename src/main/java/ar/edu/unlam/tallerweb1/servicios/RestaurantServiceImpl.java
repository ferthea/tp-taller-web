package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.RestaurantDao;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Inject
    RestaurantDao restaurantDao;

    @Override
    public List<Restaurant> obtenerListaDeRestaurants() {
        return restaurantDao.obtenerRestaurants();
    }

    @Override
    public void agregarNuevoRestaurant(Restaurant restaurant) {
    }

    @Override
    public Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception{
        return restaurantDao.obtenerRestaurantPorNombre(nombre);
    }
}
