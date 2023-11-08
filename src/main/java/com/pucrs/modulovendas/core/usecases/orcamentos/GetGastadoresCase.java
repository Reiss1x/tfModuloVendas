package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.core.domain.Orcamento;
@Service
public class GetGastadoresCase {
    @Autowired
    private OrcamentoRepo or;

    public Map<String, Double> execute(){

        List<Orcamento> orcs = or.findAll().stream().filter(p -> p.getEfetivado() == true).collect(Collectors.toList());
        
        Map<String, Double> somatorioPorNome = orcs.stream()
                .collect(Collectors.groupingBy(
                        Orcamento::getNomeCliente,
                        Collectors.summingDouble(Orcamento::getSomatorio)
                ));

        Map<String, Double> top3NomesPorSomatorio = somatorioPorNome.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
        return top3NomesPorSomatorio;
    }
    
    
}
