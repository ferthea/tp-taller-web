package ar.edu.unlam.tallerweb1.exceptions;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(String mensaje){
		super(mensaje);
	}
}
