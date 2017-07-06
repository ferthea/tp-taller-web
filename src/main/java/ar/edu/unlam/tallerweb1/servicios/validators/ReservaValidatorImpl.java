package ar.edu.unlam.tallerweb1.servicios.validators;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;
import ar.edu.unlam.tallerweb1.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service("reservaValidator")
public class ReservaValidatorImpl implements ReservaValidator {

    @Inject
    private ReservaService reservaService;

    @Autowired
    private HttpServletRequest request;

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

        return resultado;
    }

    public ValidatorResult validarReserva(Long id){
        ValidatorResult resultado = new ValidatorResult();
        User user = (User) request.getSession().getAttribute("user");
        Reserva reserva = reservaService.obtenerReservaPorId(id);

        if(user == null)
            resultado.agregarError("Debes estar logueado para realizar esta accion");


        if(reserva == null)
            resultado.agregarError("No se ha encontrado el restaurant solicitado");


        if(!reservaService.userEsDuenioDeUnaReserva(user.getId(), id))
            resultado.agregarError("No puedes realizar esta accion. No eres el creador de la reserva");

        return resultado;

    }
}
