package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.User;

public interface UserDao {
	
	List<User> getUsers();
	void registrarUsuario(User usuario);
	User consultarUsuario(User user) throws UserNotFoundException;
	User consultarUnUsuario(User user);
}
