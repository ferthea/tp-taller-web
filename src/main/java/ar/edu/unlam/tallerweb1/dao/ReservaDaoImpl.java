package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service("reservaDao")
@Transactional
public class ReservaDaoImpl implements ReservaDao{

    private final Integer MINUTO_EN_MILISEGUNDOS = 60000;
    private final Integer PAGE_SIZE = 10;

    @Inject
    private SessionFactory sessionFactory;

    public List<Reserva> obtenerReservasDeUnRestaurantParaUnaFecha(Long id, Date fecha){
        /* La duración maxima de una reserva son 90 minutos
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

    public List<Reserva> obtenerReservasDeUnUserPaginadas(Long id, Integer page){
        return (List<Reserva>) sessionFactory.getCurrentSession()
                .createCriteria(Reserva.class)
                .add(Restrictions.eq("user.id", id))
                .addOrder(CustomOrderBy.fecha("desc"))
                .setFirstResult((page - 1) * PAGE_SIZE)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setMaxResults(PAGE_SIZE)
                .list();
    }

    public List<Reserva> obtenerReservasDeUnRestaurantPaginadas(Long id, Integer page){
        return (List<Reserva>) sessionFactory.getCurrentSession()
                .createCriteria(Reserva.class)
                .add(Restrictions.eq("restaurant.id", id))
                .addOrder(CustomOrderBy.fecha("desc"))
                .setFirstResult((page - 1) * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public Long obtenerCantidadDeRerservasDeUnUser(Long id){
        return (Long) sessionFactory.getCurrentSession()
                .createCriteria(Reserva.class)
                .add(Restrictions.eq("user.id", id))
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public Long obtenerCantidadDeRerservasDeUnRestaurant(Long id){
        return (Long) sessionFactory.getCurrentSession()
                .createCriteria(Reserva.class)
                .add(Restrictions.eq("user.id", id))
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public Reserva obtenerReservaPorId(Long id){
        return (Reserva) sessionFactory.getCurrentSession()
                .createCriteria(Reserva.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

    }

    public void eliminarReserva(Long id){
        Session session = sessionFactory.getCurrentSession();
        Reserva reserva = this.obtenerReservaPorId(id);
        session.delete(reserva);
    }
}
