package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Produto;

public class GetProdsBaratosCase {
        @Autowired
        private GalpaoRepo gr;
        public Map<String, Double> execute2(){
        
        List<Produto> prods = gr.findAll();
        Map<String, Double> somatorioPorNome = prods.stream()
                .collect(Collectors.groupingBy(
                        Produto::getDesc,
                        Collectors.summingDouble(Produto::getPreco)
                ));
        Map<String, Double> top3ProdPorPreco = somatorioPorNome.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
        return top3ProdPorPreco;
        
        }
    
}
