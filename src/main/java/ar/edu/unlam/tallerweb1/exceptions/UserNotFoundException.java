package ar.edu.unlam.tallerweb1.exceptions;

/**
 * Created by fthea on 5/29/17.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String mensaje){
        super(mensaje);
    }
}
