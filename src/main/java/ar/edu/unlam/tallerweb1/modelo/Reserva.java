package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private Date fecha;
    private Integer cantidadComensales;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Pedido> pedidos = new ArrayList<>();
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedido(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void agregarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }
}
