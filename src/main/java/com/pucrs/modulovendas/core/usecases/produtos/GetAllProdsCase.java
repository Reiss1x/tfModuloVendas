package com.pucrs.modulovendas.core.usecases.produtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.usecases.orcamentos.GalpaoRepo;

public class GetAllProdsCase {
    @Autowired
    private GalpaoRepo gr;

    public List<Produto> execute(){
        return gr.findAll();
    }
}
