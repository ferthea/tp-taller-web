package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.RestaurantDao;
import ar.edu.unlam.tallerweb1.dao.UserDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception{
        return restaurantDao.obtenerRestaurantPorNombre(nombre);
    }

    public List<Restaurant> obtenerListaDeRestaurantsPorNombre(String nombre){
        return restaurantDao.obtenerListaDeRestaurantsPorNombre(nombre);
    }

    public Restaurant obtenerRestaurantPorId(Long id) throws Exception{
        return restaurantDao.obtenerRestaurantPorId(id);
    }

    public List<String> obtenerListaDeCategorias(){
        return restaurantDao.obtenerListaDeCategorias();
    }

    public List<Restaurant> obtenerListaDeRestaurantsPorCategoria(String tipo){
        return restaurantDao.obtenerListaDeRestaurantsPorCategoria(tipo);
    }

    public Boolean usuarioEsDuenioDeUnRestaurant(User usuario, Restaurant restaurant){
        for(Restaurant res : usuario.getListaDeRestaurantes()){
            if(res.getId() == restaurant.getId()) return true;
        }
        return false;
    }

    public Boolean usuearioEsDuenioDeUnRestaurant(User usuario, Long idRestaurant){
        for(Restaurant res : usuario.getListaDeRestaurantes()){
            if(res.getId() == idRestaurant) return true;
        }
        return false;
    }

    public void actualizarRestaurant(Restaurant restaurant){
        restaurantDao.actualizarRestaurant(restaurant);
    }

    public void agregarMenuAUnRestaurant(Long id, Menu menu){
        restaurantDao.agregarMenuAUnRestaurant(id, menu);
    }
}
