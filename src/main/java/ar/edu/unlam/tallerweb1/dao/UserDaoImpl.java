package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
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

	public User consultarUsuario(User user) throws UserNotFoundException{
		for(User currentUser : usuarios){
			if(currentUser.getEmail().equals(user.getEmail()) && currentUser.getPassword().equals(user.getPassword())){
				return currentUser;
			}
		}
		throw new UserNotFoundException("No se ha encontrado un usuario con los datos ingresados");
	}
}