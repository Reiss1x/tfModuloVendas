package com.pucrs.modulovendas.core.usecases.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.usecases.orcamentos.GalpaoRepo;


import org.springframework.stereotype.Service;
@Service
public class CriarProdutoCase {
    @Autowired
    private GalpaoRepo gr;

    public List<Produto> execute(List<Produto> produto){
        for(Produto p : produto){
            if(p.getQnt()<= p.getQntMax() && p.getQnt() >= p.getQntMin()){
                gr.persist(p);
            }

        }
        return produto;
    }
}
