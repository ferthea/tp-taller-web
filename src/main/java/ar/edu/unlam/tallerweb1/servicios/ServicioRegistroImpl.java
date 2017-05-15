package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.UserDao;
import ar.edu.unlam.tallerweb1.exceptions.UserAlreadyExistsException;
import ar.edu.unlam.tallerweb1.modelo.User;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro{
	
	@Inject
	private UserDao servicioRegistroDao;
	
	public void registrarUser(User user) throws UserAlreadyExistsException{
		List<User> listaDeUsuarios = servicioRegistroDao.getUsers();
		for(User currentUser : listaDeUsuarios){
			if(currentUser.getEmail().equals(user.getEmail())){
				throw new UserAlreadyExistsException("El usuario ya existe");
			}
		}
		listaDeUsuarios.add(user);
	}
}
