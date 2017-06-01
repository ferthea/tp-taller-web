package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private Integer maximaCantidadDeClientes;
    private List<Menu> listaDeMenues;

    public Restaurant(){
        this.listaDeMenues = new ArrayList<>();
    }

    public Restaurant(String nombre, String direccion, Integer cantidadDeClientes){
        this.nombre = nombre;
        this.direccion = direccion;
        this.maximaCantidadDeClientes = cantidadDeClientes;
        this.listaDeMenues = new ArrayList<>();
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

    public List<Menu> getListaDeMenues() {
        return listaDeMenues;
    }

    public void setListaDeMenues(List<Menu> listaDeMenues) {
        this.listaDeMenues = listaDeMenues;
    }

    public void agregarMenu(Menu menu){
        this.listaDeMenues.add(menu);
    }

    public void quitarMenu(Menu menu){
        this.listaDeMenues.remove(menu);
    }

    public void quitarMenuPorNombre(String nombre){
        Menu menu = new Menu();
        menu.setNombre(nombre);
        this.listaDeMenues.remove(menu);
    }
}
