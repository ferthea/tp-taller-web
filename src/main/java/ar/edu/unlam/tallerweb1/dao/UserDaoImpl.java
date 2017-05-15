package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.User;

@Service("userDao")
public class UserDaoImpl implements UserDao {
	
	private List<User> usuarios = new ArrayList<User>();
	
	public List<User> getUsers(){
		return usuarios;
	}
	
	public void registrarUsuario(User usuario){
		usuarios.add(usuario);
	}
}