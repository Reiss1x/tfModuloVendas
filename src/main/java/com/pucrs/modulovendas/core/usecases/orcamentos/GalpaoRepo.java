package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;
import java.util.Optional;

import com.pucrs.modulovendas.core.domain.Produto;

public interface GalpaoRepo {
    Produto persist(Produto prod);
    Optional<Produto> findByCod(Long cod);
    List<Produto> findAll();
}
