package com.pucrs.modulovendas.data.jb.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pucrs.modulovendas.core.domain.Orcamento;

public interface JpaOrcamentoImp extends CrudRepository<Orcamento, Long> {
    List<Orcamento> findAll();
    Optional<Orcamento> findBycod(Long cod);
}
