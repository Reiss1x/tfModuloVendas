package com.pucrs.modulovendas.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pucrs.modulovendas.entities.Produto;

public interface IGalpaoRepositoryJPA extends CrudRepository<Produto, Long> {
    Optional<Produto> findByCod(Long cod);
    List<Produto> findAll();
}
