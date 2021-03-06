package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Restaurant;
import ar.edu.unlam.tallerweb1.modelo.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("restaurantDao")
@Transactional
public class RestaurantDaoImpl implements RestaurantDao {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Restaurant> obtenerRestaurants() {
        return (List<Restaurant>) sessionFactory.getCurrentSession()
                .createCriteria(Restaurant.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Restaurant obtenerRestaurantPorNombre(String nombre) throws Exception{
        Restaurant restaurant = (Restaurant) sessionFactory.getCurrentSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.eq("nombre", nombre))
                .uniqueResult();

        if(restaurant == null) throw new Exception("Restaurant not found");
        return restaurant;
    }

    public Restaurant obtenerRestaurantPorId(Long id) throws Exception{
        Restaurant restaurant = (Restaurant) sessionFactory.getCurrentSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        if(restaurant == null) throw new Exception("Restaurant not found");
        return restaurant;
    }

    @SuppressWarnings("unchecked")
    public List<Restaurant> obtenerListaDeRestaurantsPorNombre(String nombre){
        return (List<Restaurant>) sessionFactory
                .getCurrentSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.like("nombre", nombre, MatchMode.ANYWHERE))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<String> obtenerListaDeCategorias(){
        return (List<String>) sessionFactory
                .getCurrentSession()
                .createCriteria(Restaurant.class)
                .setProjection(Projections.property("tipo"))
                .setProjection(Projections.distinct(Projections.property("tipo")))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Restaurant> obtenerListaDeRestaurantsPorCategoria(String categoria){
        return (List<Restaurant>) sessionFactory
                .getCurrentSession()
                .createCriteria(Restaurant.class)
                .add(Restrictions.eq("tipo", categoria))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public void actualizarRestaurant(Restaurant restaurant){
        sessionFactory.getCurrentSession().update(restaurant);
    }

    public void agregarMenuAUnRestaurant(Long id, Menu menu){
        Session session = sessionFactory.getCurrentSession();

        Restaurant restaurant = (Restaurant) session.
                createCriteria(Restaurant.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

        restaurant.agregarMenu(menu);

        session.saveOrUpdate(restaurant);
    }

    public Menu obtenerMenuPorId(Long id){
        return (Menu) sessionFactory.getCurrentSession()
                .createCriteria(Menu.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

}
