package ar.edu.unlam.tallerweb1.modelo.wrapper;

import ar.edu.unlam.tallerweb1.modelo.Menu;

public class PedidoHelper {
    private Long idmenu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    private Menu menu;
    private Integer cantidad = 0;

    public Long getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Long idrestaurant) {
        this.idmenu = idrestaurant;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
