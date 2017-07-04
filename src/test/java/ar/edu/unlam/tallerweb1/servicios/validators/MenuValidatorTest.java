package ar.edu.unlam.tallerweb1.servicios.validators;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuValidatorTest {


    MenuValidator menuValidator = new MenuValidatorImpl();

    @Test
    public void queLaValidacionFallePorTamanoDelNombreDelMenu(){
        Menu menu = new Menu();

        // la validacion chequea que el nombre tenga al menos 4 caracteres
        menu.setNombre("abc");
        menu.setDescripcion("descripcion del menu");
        menu.setPrecio(120.00);

        assertTrue(!menuValidator.validarMenu(menu).getResultado());
        assertTrue(menuValidator.validarMenu(menu).getErrores().get(0).equals("El nombre debe tener al menos 4 caracteres"));
    }
}
