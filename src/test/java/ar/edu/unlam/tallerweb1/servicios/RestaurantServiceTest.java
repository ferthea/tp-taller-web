package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService = new RestaurantServiceImpl();

    @Mock
    UserServiceImpl userService;

    User user;

    @Before
    public void setUp(){
        user = new User();
        user.setId((new Integer(1)).longValue());

        MockitoAnnotations.initMocks(this);

        Restaurant restaurant1 = new Restaurant();
        Restaurant restaurant2 = new Restaurant();
        restaurant1.setId((new Integer(3)).longValue());
        restaurant2.setId((new Integer(4)).longValue());
        List<Restaurant> mockedList = new ArrayList<Restaurant>();
        mockedList.add(restaurant1);
        mockedList.add(restaurant2);
        when(userService.obtenerListaDeRestaurantsDeUnUsuario(user)).thenReturn(mockedList);
    }

    @Test
    public void queRetorneFalsoSiElUsuarioNoEsDuenioDelRestaurant(){
        assertFalse(restaurantService.usuarioEsDuenioDeUnRestaurant(user, (new Integer(10)).longValue()));
    }

    @Test
    public void queRetorneVerdaderoSiElUsuarioEsDuenioDelRestaurant(){
        assertTrue(restaurantService.usuarioEsDuenioDeUnRestaurant(user, (new Integer(4)).longValue()));
    }
}
