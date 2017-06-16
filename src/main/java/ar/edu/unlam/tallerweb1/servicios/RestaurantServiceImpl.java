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
    public void agregarNuevoRestaurant(Restaurant restaurant) {
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

    @Transactional
    public void cargarMenues(){

        /*Restaurant primerRestaurant = new Restaurant("Parrilla marcelo", "Parrilla", "Rivadavia 3285", 45);
        Restaurant segundoRestaurant = new Restaurant("Pizzeria megapizzas", "Pizzeria", "Saavedra 912", 30);
        Restaurant tercerRestaurant = new Restaurant("La casa italiana", "Restaurant", "Anchorena 293", 70);

        primerRestaurant.agregarMenu(new Menu("Parrillada completa", 425.00, "Descripcion del menu...", new ArrayList<>(Arrays.asList("Carne roja", "Carne blanca"))));
        primerRestaurant.agregarMenu(new Menu("Bife de chorizo con papas fritas", 150.00, "Bife de chorizo bla bla", new ArrayList<String>(Arrays.asList("Carne roja", "papas fritas"))));

        segundoRestaurant.agregarMenu(new Menu("Grande de muzzarella", 180.00, "Descripcion pizza de muzza", new ArrayList<String>(Arrays.asList("Pizza", "Muzzarella"))));
        segundoRestaurant.agregarMenu(new Menu("Grande napolitana", 230.00, "Descripcion piza napolitana", new ArrayList<String>(Arrays.asList("Pizza", "Tomate", "Oregano"))));
        segundoRestaurant.agregarMenu(new Menu("Fugazzeta rellena", 300.00, "Descripcion fugazzeta rellena", new ArrayList<String>(Arrays.asList("Pizza", "Cebolla"))));

        tercerRestaurant.agregarMenu(new Menu("Spaghettis con salsa filetto", 130.00, "fideos...", new ArrayList<String>(Arrays.asList("Pasta"))));

        restaurantDao.agregarRestaurant(primerRestaurant);
        restaurantDao.agregarRestaurant(segundoRestaurant);
        restaurantDao.agregarRestaurant(tercerRestaurant);
        Restaurant restaurantRodolfo = new Restaurant("Superparrilla", "Parrilla", "Calle falsa 123", 30);
        User nuevoUser = new User();
        nuevoUser.setApellido("Argento");
        nuevoUser.setNombre("Rodolfo");
        nuevoUser.setPassword("password");
        nuevoUser.setEmail("rodolfo@gmail.com");
        nuevoUser.agregarNuevoRestaurant(restaurantRodolfo);
        userDao.registrarUsuario(nuevoUser);*/
    }
}
