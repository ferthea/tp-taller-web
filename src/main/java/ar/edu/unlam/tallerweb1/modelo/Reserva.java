package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Reserva {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String codigo;
	@ManyToOne(cascade = CascadeType.ALL)
	private User usuario;
	@ManyToOne(cascade = CascadeType.ALL)
	private Restaurant restaurant;
	private Date fechaHora;
	@OneToMany
	private List<Pedido> listaDePedidos;
	
	public Reserva(){
	}
	
	public Reserva(String codigo, User usuario, Restaurant restaurant){
		this.codigo = codigo;
		this.usuario = usuario;
		this.restaurant = restaurant;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public List<Pedido> getListaDePedidos() {
		return listaDePedidos;
	}

	public void setListaDePedidos(List<Pedido> listaDePedidos) {
		this.listaDePedidos = listaDePedidos;
	}
	
	public void addPedido(Pedido pedido){
		this.listaDePedidos.add(pedido);
	}
	 
	
}
