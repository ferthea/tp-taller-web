package ar.edu.unlam.tallerweb1.modelo.wrapper;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoListWrapper {
    private List<Pedido> pedidosList;

    public PedidoListWrapper(){
        this.pedidosList = new ArrayList<Pedido>();
    }

    public List<Pedido> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedido> pedidosList) {
        this.pedidosList = pedidosList;
    }

    public void agregarPedido(Pedido pedido){
        this.pedidosList.add(pedido);
    }
}
