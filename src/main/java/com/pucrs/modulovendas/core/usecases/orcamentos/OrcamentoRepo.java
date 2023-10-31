package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.core.domain.Orcamento;
@Repository
public interface OrcamentoRepo {
    Optional<Orcamento> findByCod(Long cod);
    List<Orcamento> findAll();
    Orcamento persist(Orcamento orc);
}
