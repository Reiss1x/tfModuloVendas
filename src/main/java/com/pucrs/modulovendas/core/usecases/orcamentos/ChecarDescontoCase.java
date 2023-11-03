package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.core.domain.Orcamento;
@Service
public class ChecarDescontoCase {
    @Autowired
    private OrcamentoRepo or;

    public double execute(String clienteName){
        List<Orcamento> orcs = or.findBynomeCliente(clienteName);
        
        double desconto = primeiroDesconto(orcs, clienteName);
        double desconto2 = segundoDesconto(orcs, clienteName);

        if(desconto > desconto2){ return desconto; } else { return desconto2; }
        
        
        
    }

    public Double primeiroDesconto(List<Orcamento> orcs, String clienteName){
        double desconto = 0.0;
        if(orcs.size() >= 1){
            
            Collections.reverse(orcs);
            List<Orcamento> ultimosTres = orcs.subList(0,Math.min(3, orcs.size()));
            double soma = ultimosTres.stream().mapToDouble(Orcamento::getSomatorio).sum();
            
            if (soma >= 10000) {
            desconto = 0.10;
            double valorExcedente = soma - 10000;
            while (valorExcedente >= 10000 && desconto < 0.30) {
                valorExcedente -= 10000;
                desconto += 0.05;
                }
            }
        
        }
        return desconto;
    }

    public Double segundoDesconto(List<Orcamento> orcs, String clienteName){
        long count = 0;

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataAtual = LocalDate.now();

        count = orcs.stream().filter(obj -> {
        LocalDate dataObjeto = LocalDate.parse(obj.getData(), formatador);
        return dataObjeto.isAfter(dataAtual.minusMonths(6));
        }).count();
        
        if(count >= 10){
            return 0.25;
        } else { return 0.0;}
    }
}
