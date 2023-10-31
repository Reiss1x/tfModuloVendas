package com.pucrs.modulovendas.data.jb.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.usecases.pedidos.PedidoRepo;
@Repository
public class PedidoRepoImp implements PedidoRepo {

    @Autowired
    private JpaPedidoImp repo;

    @Override
    public Pedido persist(Pedido pedido) {
        return repo.save(pedido);
    }

    @Override
    public List<Pedido> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Pedido> findByCod(Long cod) {
        return repo.findBycod(cod);
    }
    
}
