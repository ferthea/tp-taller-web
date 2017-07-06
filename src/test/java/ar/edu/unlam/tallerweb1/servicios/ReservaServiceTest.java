package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.ReservaDao;
import ar.edu.unlam.tallerweb1.exceptions.NoTableAvailableException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @InjectMocks
    ReservaService reservaService = spy(ReservaServiceImpl.class);

    @Mock
    ReservaDao reservaDao;

    Reserva reserva;

    @Before
    public void setUp(){
        reserva = new Reserva();
        Long restaurantId = (new Integer(1)).longValue();
        Date fecha = new Date();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        reserva.setCantidadComensales(4);
        reserva.setRestaurant(restaurant);
        reserva.setFecha(fecha);

        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NoTableAvailableException.class)
    public void queNoSePuedaReservarCuandoNoHayLugar() throws NoTableAvailableException {
        doReturn(3).when(reservaService)
                .obtenerLugaresDisponiblesParaUnHorario(reserva.getRestaurant().getId(), reserva.getFecha());

        reservaService.registrarReserva(reserva);
    }

    @Test
    public void queSePuedaReservarCuandoHayLugar(){
        doReturn(10).when(reservaService)
                .obtenerLugaresDisponiblesParaUnHorario(reserva.getRestaurant().getId(), reserva.getFecha());

        try{
            reservaService.registrarReserva(reserva);
            verify(reservaDao, times(1)).registrarReserva(reserva);
        }catch (NoTableAvailableException e){
            fail("no debería haber fallado");
        }
    }
}
