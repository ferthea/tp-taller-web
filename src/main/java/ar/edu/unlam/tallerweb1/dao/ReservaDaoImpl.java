package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service("reservaDao")
@Transactional
public class ReservaDaoImpl implements ReservaDao{

    final Integer MINUTO_EN_MILISEGUNDOS = 60000;

    @Inject
    private SessionFactory sessionFactory;

    public List<Reserva> obtenerReservasDeUnRestaurantParaUnaFecha(Long id, Date fecha){
        /* Tomamos como * que la duración maxima de una reserva son 90 minutos
         * por lo tanto, para chequear si una reserva tiene 'lugares diponibles'
         * necesitamos ver cuantas reservas hay DESDE 90 minutos antes HASTA 90 min despues
         */
        Date horaInicio = new Date(fecha.getTime() - (90 * MINUTO_EN_MILISEGUNDOS));
        Date horaFin = new Date(fecha.getTime() + (90 * MINUTO_EN_MILISEGUNDOS));

        return (List<Reserva>) sessionFactory.getCurrentSession()
                .createCriteria(Reserva.class)
                .add(Restrictions.eq("restaurant.id", id))
                .add(Restrictions.gt("fecha", horaInicio))
                .add(Restrictions.le("fecha", horaFin))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public void registrarReserva(Reserva reserva){
        sessionFactory.getCurrentSession().save(reserva);
    }
}
