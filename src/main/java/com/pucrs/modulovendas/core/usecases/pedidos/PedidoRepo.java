package com.pucrs.modulovendas.core.usecases.pedidos;

import java.util.List;
import java.util.Optional;

import com.pucrs.modulovendas.core.domain.Pedido;

public interface PedidoRepo {
    Pedido persist(Pedido pedido);
    List<Pedido> findAll();
    Optional<Pedido> findByCod(Long cod);
}
