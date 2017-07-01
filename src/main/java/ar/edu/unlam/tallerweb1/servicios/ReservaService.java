package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.util.Date;

public interface ReservaService {
    Integer obtenerLugaresDisponiblesParaUnHorario(Long idRestaurant, Date horario);
    void registrarReserva(Reserva reserva);
}
