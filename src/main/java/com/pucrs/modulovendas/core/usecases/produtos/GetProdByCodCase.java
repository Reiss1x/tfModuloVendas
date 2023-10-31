package com.pucrs.modulovendas.core.usecases.produtos;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.usecases.orcamentos.GalpaoRepo;


import org.springframework.stereotype.Service;
@Service
public class GetProdByCodCase {
    @Autowired
    private GalpaoRepo gr;

    public Produto execute(Long cod){
        if(gr.findByCod(cod).isPresent()){
            return gr.findByCod(cod).get();
        } else {
            throw new IllegalArgumentException("Produto: "+ cod + " não existe.");
        }
    }
}
