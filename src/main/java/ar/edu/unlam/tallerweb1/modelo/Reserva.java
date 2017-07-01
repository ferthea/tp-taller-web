package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;
    private Date fecha = new Date();
    private Integer cantidadComensales;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Menu> pedido;
    private String observaciones;

    public Reserva(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date setFecha(){
        return this.fecha;
    }

    public void getFecha(Date fecha){
        this.fecha = fecha;
    }

    public Integer getCantidadComensales() {
        return cantidadComensales;
    }

    public void setCantidadComensales(Integer cantidad_comensales) {
        this.cantidadComensales = cantidad_comensales;
    }

    public List<Menu> getPedido() {
        return pedido;
    }

    public void setPedido(List<Menu> pedido) {
        this.pedido = pedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
