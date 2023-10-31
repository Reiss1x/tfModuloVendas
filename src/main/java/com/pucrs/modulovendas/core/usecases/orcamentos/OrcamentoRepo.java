package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;
import java.util.Optional;

import com.pucrs.modulovendas.core.domain.Orcamento;

public interface OrcamentoRepo {
    Optional<Orcamento> findByCod(Long cod);
    List<Orcamento> findAll();
    Orcamento persist(Orcamento orc);
}
