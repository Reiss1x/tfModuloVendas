package com.pucrs.modulovendas.core.usecases.orcamentos.descontos;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.usecases.orcamentos.OrcamentoRepo;

@Service
public class DezCompras implements ICalculaDesconto {

    @Autowired
    private OrcamentoRepo or;

    @Override
    public double calcula(String clienteName) {
        double desconto = 0.0;
        List<Orcamento> orcs = or.findBynomeCliente(clienteName).stream().filter(i -> i.getEfetivado() == true).collect(Collectors.toList());

        if(orcs.size() >= 3){
            
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
    
}
