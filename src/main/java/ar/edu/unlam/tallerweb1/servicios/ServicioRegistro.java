package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.exceptions.UserAlreadyExistsException;
import ar.edu.unlam.tallerweb1.modelo.User;

public interface ServicioRegistro {
	
	void registrarUser (User user) throws UserAlreadyExistsException;
}
