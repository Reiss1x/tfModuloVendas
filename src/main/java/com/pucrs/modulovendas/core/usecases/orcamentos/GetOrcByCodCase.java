package com.pucrs.modulovendas.core.usecases.orcamentos;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Orcamento;

/**
 * GetOrcById
 */
public class GetOrcByCodCase {

    @Autowired
    private OrcamentoRepo or;
    
    public Orcamento execute(Long orcamentoCod) {
        if(or.findByCod(orcamentoCod).isPresent()){
            return or.findByCod(orcamentoCod).get();
        } else {
            throw new IllegalArgumentException("Orcamento: "+ orcamentoCod +" não existe");
        }
    }
}