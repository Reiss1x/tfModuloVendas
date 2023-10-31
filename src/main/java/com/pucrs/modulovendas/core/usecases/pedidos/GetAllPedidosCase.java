package com.pucrs.modulovendas.core.usecases.pedidos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Pedido;

import org.springframework.stereotype.Service;
@Service
public class GetAllPedidosCase {
    @Autowired
    private PedidoRepo pr;

    public List<Pedido> execute() {
        return pr.findAll();
    }
    
}
