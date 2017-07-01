package ar.edu.unlam.tallerweb1.dao;


import ar.edu.unlam.tallerweb1.modelo.Reserva;

import java.util.Date;
import java.util.List;

public interface ReservaDao {
    List<Reserva> obtenerReservasDeUnRestaurantParaUnaFecha(Long id, Date fecha);
    void registrarReserva(Reserva reserva);
}
