package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Orcamento;

public class GetRelatorioCase {
    @Autowired
    private OrcamentoRepo or;

    public List<Orcamento> execute() {
        return or.findAll().stream().filter(x -> x.getEfetivado() == true).collect(Collectors.toList());
    }
}
