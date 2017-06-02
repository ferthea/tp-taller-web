package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.User;

import javax.inject.Inject;

@Service("userDao")
public class UserDaoImpl implements UserDao {

	@Inject
	private SessionFactory sessionFactory;
	
	private List<User> usuarios = new ArrayList<User>();
	
	public List<User> getUsers(){
		return usuarios;
	}
	
	public void registrarUsuario(User usuario){
		usuarios.add(usuario);
	}

	public User consultarUsuario(User user) throws UserNotFoundException{
		for(User currentUser : usuarios){
			if(currentUser.getEmail().equals(user.getEmail()) && currentUser.getPassword().equals(user.getPassword())){
				return currentUser;
			}
		}
		throw new UserNotFoundException("No se ha encontrado un usuario con los datos ingresados");
	}

	//BDD
	public User consultarUnUsuario(User usuario){
		final Session session = sessionFactory.getCurrentSession();
		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}
}