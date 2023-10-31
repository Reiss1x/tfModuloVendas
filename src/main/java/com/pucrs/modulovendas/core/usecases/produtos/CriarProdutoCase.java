package com.pucrs.modulovendas.core.usecases.produtos;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.usecases.orcamentos.GalpaoRepo;

public class CriarProdutoCase {
    @Autowired
    private GalpaoRepo gr;

    public Produto execute(Produto produto){
        return gr.persist(produto);
    }
}
