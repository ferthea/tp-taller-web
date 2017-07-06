package ar.edu.unlam.tallerweb1.servicios.validators;


import ar.edu.unlam.tallerweb1.modelo.validator.ValidatorResult;

public interface ReservaValidator {

    ValidatorResult validarCantidadDeComensales(Long restaurant_id, Long fecha, Integer cantidad);
    ValidatorResult validarReserva(Long id);
}
