package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ReservaDao;
import ar.edu.unlam.tallerweb1.exceptions.NoTableAvailableException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service("reservaService")
public class ReservaServiceImpl implements ReservaService {

    @Inject
    private RestaurantService restaurantService;

    @Inject
    private ReservaDao reservaDao;

    public Integer obtenerLugaresDisponiblesParaUnHorario(Long idRestaurant, Date horario){
        Integer total = 0;
        List<Reserva> reservas = reservaDao.obtenerReservasDeUnRestaurantParaUnaFecha(idRestaurant, horario);
        for(Reserva reserva : reservas){
            total += reserva.getCantidadComensales();
        }

        try{
            Restaurant restaurant = restaurantService.obtenerRestaurantPorId(idRestaurant);
            return restaurant.getMaximaCantidadDeClientes() - total;
        }catch(Exception e){
            System.err.print("Error: " + e);
        }

        return 0;
    }

    public void registrarReserva(Reserva reserva) throws NoTableAvailableException {
        if(reserva.getCantidadComensales() > this.obtenerLugaresDisponiblesParaUnHorario(reserva.getRestaurant().getId(), reserva.getFecha())){
            throw new NoTableAvailableException("No hay lugar disponible");
        }
        reservaDao.registrarReserva(reserva);
    }

    public List<Reserva> obtenerReservasDeUnUserPaginadas(Long id, Integer page){
        return reservaDao.obtenerReservasDeUnUserPaginadas(id, page);
    }

    public List<Reserva> obtenerReservasDeUnRestaurantPaginadas(Long id, Integer page){
        return reservaDao.obtenerReservasDeUnRestaurantPaginadas(id, page);
    }

    public Long obtenerCantidadDeRerservasDeUnUser(Long id){
        return reservaDao.obtenerCantidadDeRerservasDeUnUser(id);
    }

    public Long obtenerCantidadDeReservasDeUnRestaurant(Long id){
        return reservaDao.obtenerCantidadDeRerservasDeUnRestaurant(id);
    }

}
