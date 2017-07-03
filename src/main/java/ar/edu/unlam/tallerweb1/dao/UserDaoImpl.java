package ar.edu.unlam.tallerweb1.dao;

import java.beans.Transient;
import java.util.List;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Inject
	private SessionFactory sessionFactory;

	public List<User> getUsers(){
		Session session = sessionFactory.getCurrentSession();
		List<User> listaDeUsuarios = session.createCriteria(User.class).list();
		return listaDeUsuarios;
	}

	@Override
	public void agregarRestaurantAUnUsuario(User user, Restaurant restaurant){
		Session session = sessionFactory.getCurrentSession();
		User currentUser = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("id", user.getId()))
				.uniqueResult();

		currentUser.agregarNuevoRestaurant(restaurant);

		session.update(currentUser);
	}

	public void registrarUsuario(User usuario){
		Session session = sessionFactory.getCurrentSession();
		session.save(usuario);
	}

	public User consultarUsuario(User user) throws UserNotFoundException{
		Session session = sessionFactory.getCurrentSession();
		User usuario = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("email", user.getEmail()))
				.add(Restrictions.eq("password", user.getPassword()))
				.uniqueResult();

		if(usuario == null){
			throw new UserNotFoundException("No se ha encontrado un usuario con los datos ingresados");
		}
		return usuario;
	}

	public List<Restaurant> obtenerListaDeRestaurantesPorUsuario(User user){
		User usuario = (User) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("id", user.getId()))
				.uniqueResult();
		System.out.print(usuario.getListaDeRestaurantes());
		return usuario.getListaDeRestaurantes();
	}
}