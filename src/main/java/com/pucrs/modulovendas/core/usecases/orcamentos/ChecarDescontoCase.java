package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Orcamento;

public class ChecarDescontoCase {
    @Autowired
    private OrcamentoRepo or;

    public double execute(String clienteName){
        List<Orcamento> orcs = or.findAll();
        
        double desconto = primeiroDesconto(orcs, clienteName);
        double desconto2 = segundoDesconto(orcs, clienteName);

        if(desconto > desconto2){ return desconto; } else { return desconto2; }
        
        
        
    }

    public Double primeiroDesconto(List<Orcamento> orcs, String clienteName){
        int count = 0;
        double somaCompras = 0;
        double desconto = 0;
        for(Orcamento orc : orcs){
            if(orc.getNomeCliente() == clienteName){
                count++;
                somaCompras+= orc.getSomatorio();
            }
        }
        if(count >= 3){
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            orcs.sort((obj1, obj2) ->
                    LocalDate.parse(obj1.getData(), dateFormatter)
                            .compareTo(LocalDate.parse(obj2.getData(), dateFormatter))
            );
            List<Orcamento> ultimosTres = orcs.subList(0, 2);
            double soma = ultimosTres.stream().mapToDouble(Orcamento::getSomatorio).sum();
            
            if (soma > 10000) {
                desconto = 0.10;
                double valorExcedente = soma - 10000;
                desconto += Math.min(0.30, (valorExcedente / 10000) * 0.05);
            }
        }
        return desconto;
    }

    public Double segundoDesconto(List<Orcamento> orcs, String clienteName){
        long count = 0;
        double somaCompras = 0;
        double desconto = 0;
        for(Orcamento orc : orcs){
            if(orc.getNomeCliente() == clienteName){
                count++;
                somaCompras+= orc.getSomatorio();
            }
        }

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataAtual = LocalDate.now();

        count = orcs.stream().filter(obj -> {
        LocalDate dataObjeto = LocalDate.parse(obj.getData(), formatador);
        return dataObjeto.isAfter(dataAtual.minusMonths(6));
        }).count();
        
        if(count >= 10){
            return desconto = 25;
        } else { return 0.0;}
    }
}
