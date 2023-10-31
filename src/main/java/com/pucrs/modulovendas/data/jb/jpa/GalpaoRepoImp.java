package com.pucrs.modulovendas.data.jb.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.usecases.orcamentos.GalpaoRepo;

@Repository
public class GalpaoRepoImp implements GalpaoRepo {

    private JpaGalpaoRepo repo;

    @Override
    public Produto persist(Produto produto){
        return repo.save(produto);
    }

    @Override
    public Optional<Produto> findByCod(Long id) {
        return repo.findByCod(id);
    }

    @Override
    public List<Produto> findAll() {
        return repo.findAll();
    }
}
