package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.core.domain.Produto;
@Repository
public interface GalpaoRepo {
    Produto persist(Produto prod);
    Optional<Produto> findByCod(Long cod);
    List<Produto> findAll();
}
