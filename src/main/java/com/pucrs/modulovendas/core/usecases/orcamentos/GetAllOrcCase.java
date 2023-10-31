package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Orcamento;

public class GetAllOrcCase {
    
    @Autowired
    private OrcamentoRepo or;

    public List<Orcamento> execute() {
        return or.findAll();
    }
}
