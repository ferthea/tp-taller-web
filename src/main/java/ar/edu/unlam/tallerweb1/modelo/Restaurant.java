package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.enums.TipoDeRestaurant;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    private String direccion;
    private Integer maximaCantidadDeClientes;
    private String foto;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Menu> listaDeMenues;

    public Restaurant(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getMaximaCantidadDeClientes() {
        return maximaCantidadDeClientes;
    }

    public void setMaximaCantidadDeClientes(Integer maximaCantidadDeClientes) {
        this.maximaCantidadDeClientes = maximaCantidadDeClientes;
    }

    public String getFoto(){
        return foto;
    }

    public void setFoto(String foto){
        this.foto = foto;
    }


    public List<Menu> getListaDeMenues() {
        return listaDeMenues;
    }

    public void setListaDeMenues(List<Menu> listaDeMenues) {
        this.listaDeMenues = listaDeMenues;
    }

    public void agregarMenu(Menu menu){
        this.listaDeMenues.add(menu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        return !(nombre != null ? !nombre.equals(that.nombre) : that.nombre != null);

    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
}
