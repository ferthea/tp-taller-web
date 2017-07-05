package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.exceptions.NoTableAvailableException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.util.Date;
import java.util.List;

public interface ReservaService {
    Integer obtenerLugaresDisponiblesParaUnHorario(Long idRestaurant, Date horario);
    void registrarReserva(Reserva reserva) throws NoTableAvailableException;
    List<Reserva> obtenerReservasDeUnUserPaginadas(Long id, Integer page);
    List<Reserva> obtenerReservasDeUnRestaurantPaginadas(Long id, Integer page);
    Long obtenerCantidadDeRerservasDeUnUser(Long id);
    Long obtenerCantidadDeReservasDeUnRestaurant(Long id);
}
