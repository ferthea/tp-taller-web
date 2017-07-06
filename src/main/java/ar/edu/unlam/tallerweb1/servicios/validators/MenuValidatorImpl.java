package ar.edu.unlam.tallerweb1.servicios.validators;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import org.springframework.stereotype.Service;

@Service("menuValidator")
public class MenuValidatorImpl implements MenuValidator {

    public ValidatorResult validarMenu(Menu menu){
        ValidatorResult resultado = new ValidatorResult();
        if(menu.getNombre().length() < 4){
            resultado.agregarError("El nombre debe tener al menos 4 caracteres");
        }

        if(menu.getPrecio().isNaN()){
            resultado.agregarError("Ingrese un precio valido");
        }

        if(resultado.getErrores().size() == 0) resultado.setResultado(true);

        return resultado;
    }
}
