package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.User;

public interface UserDao {
	
	public List<User> getUsers();
	public void registrarUsuario(User usuario);
}
