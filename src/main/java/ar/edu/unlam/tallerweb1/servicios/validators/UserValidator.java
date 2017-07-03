package ar.edu.unlam.tallerweb1.servicios.validators;


import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;

public interface UserValidator {
    ValidatorResult validarUsuario(User user);
}
