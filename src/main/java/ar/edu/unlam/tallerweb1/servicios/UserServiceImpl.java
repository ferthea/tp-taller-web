package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.UserDao;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Inject
    private UserDao userDao;

    public void agregarRestaurantAUnUsuario(User user, Restaurant restaurant){
        userDao.agregarRestaurantAUnUsuario(user, restaurant);
    }


    public List<Restaurant> obtenerListaDeRestaurantsDeUnUsuario(User user){
        return userDao.obtenerListaDeRestaurantesPorUsuario(user);
    }

    public User obtenerUserPorId(Long id){
        return userDao.obtenerUserPorId(id);
    }
}
