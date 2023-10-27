package com.pucrs.modulovendas.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pucrs.modulovendas.entities.Orcamento;

public interface IOrcamentoRepositoryJPA extends CrudRepository<Orcamento, Long> {
    List<Orcamento> findAll();
    Optional<Orcamento> findBycod(Long cod);
}
