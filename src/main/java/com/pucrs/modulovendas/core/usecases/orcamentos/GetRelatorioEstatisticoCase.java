package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.core.domain.Orcamento;
@Service
public class GetRelatorioEstatisticoCase {
    @Autowired
    private OrcamentoRepo or;


    public List<String> execute(){
        
        List<Orcamento> orcs = or.findAll().stream().filter(p -> p.getEfetivado() == true).collect(Collectors.toList());
        

        Map<String, Long> contagemNomes = orcs.stream()
                .collect(Collectors.groupingBy(Orcamento::getNomeCliente, Collectors.counting()));

        List<String> top3Compradores = contagemNomes.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return top3Compradores;
    }
}
