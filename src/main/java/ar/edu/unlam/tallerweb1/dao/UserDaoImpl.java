package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import org.hibernate.HibernateException;
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

	public List<User> getUsers(){
		Session session = generateSession();
		List<User> listaDeUsuarios = session.createCriteria(User.class).list();
		return listaDeUsuarios;
	}
	
	public void registrarUsuario(User usuario){
		Session session = generateSession();
		session.save(usuario);
	}

	public User consultarUsuario(User user) throws UserNotFoundException{
		Session session = generateSession();
		User usuario = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("email", user.getEmail()))
				.add(Restrictions.eq("password", user.getPassword()))
				.uniqueResult();
		if(usuario != null){
			return usuario;
		}
		throw new UserNotFoundException("No se ha encontrado un usuario con los datos ingresados");
	}

	//BDD
	public User consultarUnUsuario(User usuario){
		Session session = generateSession();
		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
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