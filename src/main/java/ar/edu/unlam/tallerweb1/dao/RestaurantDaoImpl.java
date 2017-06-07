package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("restaurantDao")
public class RestaurantDaoImpl implements RestaurantDao {

    private List<Restaurant> listaDeRestaurants;

    @Inject
    private SessionFactory sessionFactory;

    @PostConstruct
    public void generarRestaurants(){
        Session session = generateSession();
        listaDeRestaurants = new ArrayList<>();
        Restaurant primerRestaurant = new Restaurant("Parrilla marcelo", "Parrilla", "Rivadavia 3285", 45);
        Restaurant segundoRestaurant = new Restaurant("Pizzeria megapizzas", "Pizzeria", "Saavedra 912", 30);
        Restaurant tercerRestaurant = new Restaurant("La casa italiana", "Restaurant", "Anchorena 293", 70);
        primerRestaurant.agregarMenu(new Menu("Parrillada completa", 425.00, "Descripcion del menu...", new ArrayList<>(Arrays.asList("Carne roja", "Carne blanca"))));
        primerRestaurant.agregarMenu(new Menu("Bife de chorizo con papas fritas", 150.00, "Bife de chorizo bla bla", new ArrayList<String>(Arrays.asList("Carne roja", "papas fritas"))));
        segundoRestaurant.agregarMenu(new Menu("Grande de muzzarella", 180.00, "Descripcion pizza de muzza", new ArrayList<String>(Arrays.asList("Pizza", "Muzzarella"))));
        segundoRestaurant.agregarMenu(new Menu("Grande napolitana", 230.00, "Descripcion piza napolitana", new ArrayList<String>(Arrays.asList("Pizza", "Tomate", "Oregano"))));
        segundoRestaurant.agregarMenu(new Menu("Fugazzeta rellena", 300.00, "Descripcion fugazzeta rellena", new ArrayList<String>(Arrays.asList("Pizza", "Cebolla"))));
        tercerRestaurant.agregarMenu(new Menu("Spaghettis con salsa filetto", 130.00, "fideos...", new ArrayList<String>(Arrays.asList("Pasta"))));

        session.save(primerRestaurant);
        session.save(segundoRestaurant);
        session.save(tercerRestaurant);
        this.listaDeRestaurants.add(primerRestaurant);
        this.listaDeRestaurants.add(segundoRestaurant);
        this.listaDeRestaurants.add(tercerRestaurant);
    }

    @Override
    public List<Restaurant> obtenerRestaurants() {
        return this.listaDeRestaurants;
    }

    @Override
    public void agregarRestaurant(Restaurant restaurant) {
        this.listaDeRestaurants.add(restaurant);
    }

    @Override
    public Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception{
        for(Restaurant restaurant : listaDeRestaurants){
            if(restaurant.getNombre().equals(nombre)){
                return restaurant;
            }
        }
        throw new Exception("Restaurant not found");
    }

    private Session generateSession(){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
        } catch(HibernateException exception){
            session = sessionFactory.openSession();
        }
        return session;
    }
}
