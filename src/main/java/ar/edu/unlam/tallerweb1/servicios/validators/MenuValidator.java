package ar.edu.unlam.tallerweb1.servicios.validators;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;

public interface MenuValidator {
    ValidatorResult validarMenu(Menu menu);
}
