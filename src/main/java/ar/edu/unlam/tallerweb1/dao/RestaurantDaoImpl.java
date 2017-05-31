package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("restaurantDao")
public class RestaurantDaoImpl implements RestaurantDao {

    private List<Restaurant> listaDeRestaurants;

    @PostConstruct
    public void generarRestaurants(){
        Restaurant primerRestaurant = new Restaurant("Parrilla marcelo", "Rivadavia 3285", 45);
        Restaurant segundoRestaurant = new Restaurant("Pizzeria megapizzas", "Saavedra 912", 30);
        primerRestaurant.agregarMenu(new Menu("Parrillada completa", 425.00, "Descripcion del menu..."));
        primerRestaurant.agregarMenu(new Menu("Bife de chorizo con papas fritas", 150.00, "Bife de chorizo bla bla"));
        segundoRestaurant.agregarMenu(new Menu("Grande de muzzarella", 180.00, "Descripcion pizza de muzza"));
        segundoRestaurant.agregarMenu(new Menu("Grande napolitana", 230.00, "Descripcion piza napolitana"));
        segundoRestaurant.agregarMenu(new Menu("Fugazzetta rellena", 300.00, "Descripcion fugazzeta rellena"));

        this.listaDeRestaurants.add(primerRestaurant);
        this.listaDeRestaurants.add(segundoRestaurant);
    }

    @Override
    public List<Restaurant> obtenerRestaurants() {
        return this.listaDeRestaurants;
    }

    @Override
    public void agregarRestaurant(Restaurant restaurant) {
        this.listaDeRestaurants.add(restaurant);
    }
}
