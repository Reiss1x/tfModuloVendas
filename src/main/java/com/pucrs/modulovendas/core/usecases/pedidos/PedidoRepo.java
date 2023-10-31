package com.pucrs.modulovendas.core.usecases.pedidos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.core.domain.Pedido;
@Repository
public interface PedidoRepo {
    Pedido persist(Pedido pedido);
    List<Pedido> findAll();
    Optional<Pedido> findByCod(Long cod);
}
