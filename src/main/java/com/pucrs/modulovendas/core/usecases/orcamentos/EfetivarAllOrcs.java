package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.core.domain.Orcamento;

@Service
public class EfetivarAllOrcs {
    @Autowired
    private OrcamentoRepo or;
    @Autowired
    private EfetivarOrcCase efetiva;
    public String execute(){
        List<Orcamento> orcs = or.findAll();

        for(Orcamento orc : orcs){
            efetiva.execute(orc.getCod());
        }
        return "Todos orcamentos efetivados.";
    }
}
