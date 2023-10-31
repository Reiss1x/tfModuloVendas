package com.pucrs.modulovendas.data.jb.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pucrs.modulovendas.core.domain.Pedido;

public interface JpaPedidoImp extends CrudRepository<Pedido, Long>{
    List<Pedido> findAll();
    Optional<Pedido> findBycod(Long cod);
}
