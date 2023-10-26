package com.pucrs.modulovendas.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pucrs.modulovendas.entities.Pedido;

public interface IPedidosRepositoryJPA extends CrudRepository<Pedido, Long>{
    List<Pedido> findAll();
    Optional<Pedido> findBycod(Long cod);
}
