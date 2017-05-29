package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.exceptions.UserNotFoundException;
import ar.edu.unlam.tallerweb1.modelo.User;

public interface LoginService {
    User consultarUsuario(User user) throws UserNotFoundException;
}
