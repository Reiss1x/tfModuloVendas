package com.pucrs.modulovendas.domain;

import com.pucrs.modulovendas.entities.Pedido;

public interface IPedidoRepository {
    public Pedido getPedido();
    public void addPedidos();
}
