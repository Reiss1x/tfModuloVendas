package com.pucrs.modulovendas.data.jb.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.usecases.orcamentos.OrcamentoRepo;

@Repository
public class OrcamentoRepoImp implements OrcamentoRepo {
    @Autowired
    private JpaOrcamentoImp repo;

    @Override
    public Optional<Orcamento> findByCod(Long cod) {
        return repo.findBycod(cod);
    }

    @Override
    public List<Orcamento> findAll() {
        return repo.findAll();
    }

    @Override
    public Orcamento persist(Orcamento orc) {
        return repo.save(orc);
    }

    @Override
    public List<Orcamento> findBynomeCliente(String nomeCliente) {
        return repo.findBynomeCliente(nomeCliente);
    }
    
}
