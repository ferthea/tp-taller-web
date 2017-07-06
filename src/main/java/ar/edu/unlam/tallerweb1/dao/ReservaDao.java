package ar.edu.unlam.tallerweb1.dao;


import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.util.Date;
import java.util.List;

public interface ReservaDao {
    List<Reserva> obtenerReservasDeUnRestaurantParaUnaFecha(Long id, Date fecha);
    void registrarReserva(Reserva reserva);
    List<Reserva> obtenerReservasDeUnUserPaginadas(Long id, Integer page);
    List<Reserva> obtenerReservasDeUnRestaurantPaginadas(Long id, Integer page);
    Long obtenerCantidadDeRerservasDeUnUser(Long id);
    Long obtenerCantidadDeRerservasDeUnRestaurant(Long id);
    Reserva obtenerReservaPorId(Long id);
    void eliminarReserva(Long id);
}
