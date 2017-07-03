package ar.edu.unlam.tallerweb1.modelo.wrapper;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoListWrapper {
    private List<PedidoHelper> pedidosList;

    public PedidoListWrapper(){
        this.pedidosList = new ArrayList<PedidoHelper>();
    }

    public List<PedidoHelper> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<PedidoHelper> pedidosList) {
        this.pedidosList = pedidosList;
    }

    public void agregarPedido(PedidoHelper pedido){
        this.pedidosList.add(pedido);
    }
}
