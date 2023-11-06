package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.LinkedHashMap;
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


    // public Map<String, Long> execute(){
        
    //     List<Orcamento> orcs = or.findAll().stream().filter(p -> p.getEfetivado() == true).collect(Collectors.toList());
        

    //     Map<String, Long> contagemNomes = orcs.stream()
    //             .collect(Collectors.groupingBy(Orcamento::getNomeCliente, Collectors.counting()));

    //     Map<String, Long> top3Compradores = contagemNomes.entrySet().stream()
    //             .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
    //             .limit(3)
    //             .collect(Collectors.toMap(
    //                     Map.Entry::getKey,
    //                     Map.Entry::getValue,
    //                     (existing, replacement) -> existing,
    //                     LinkedHashMap::new // Para manter a ordem de classificação
    //             ));
    //     return top3Compradores;
    // }
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
                        LinkedHashMap::new // Para manter a ordem de classificação
                ));
        return top3NomesPorSomatorio;
    }
}
