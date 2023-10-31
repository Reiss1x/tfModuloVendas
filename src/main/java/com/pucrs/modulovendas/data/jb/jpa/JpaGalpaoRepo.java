package com.pucrs.modulovendas.data.jb.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pucrs.modulovendas.core.domain.Produto;

public interface JpaGalpaoRepo extends CrudRepository<Produto, Long> {
    Optional<Produto> findByCod(Long cod);
    List<Produto> findAll();
}
