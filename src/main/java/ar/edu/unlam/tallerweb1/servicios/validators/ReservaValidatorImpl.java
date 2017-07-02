package ar.edu.unlam.tallerweb1.servicios.validators;

import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import ar.edu.unlam.tallerweb1.servicios.ReservaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service("reservaValidator")
public class ReservaValidatorImpl implements ReservaValidator {

    @Inject
    private ReservaService reservaService;

    public ValidatorResult validarCantidadDeComensales(Long restaurant_id, Long fecha, Integer cantidad){
        ValidatorResult resultado = new ValidatorResult();
        Integer lugares_disponibles = reservaService.obtenerLugaresDisponiblesParaUnHorario(restaurant_id, new Date(fecha));

        if(fecha < new Date().getTime()){
            System.err.print("fecha: " + fecha + " - ahora: " + new Date().getTime());
            resultado.agregarError("No puedes reservar en el pasado. A menos que tengas un DeLorean dmc-12 :)");
        }

        if(lugares_disponibles < cantidad) {
            resultado.agregarError("No hay lugares disponibles para esa hora. (Disponibles: " + lugares_disponibles + " - Cantidad seleccionada: " + cantidad + ")");
        }

        if(resultado.getErrores().size() == 0){
            resultado.setResultado(true);
        }

        return resultado;
    }
}
