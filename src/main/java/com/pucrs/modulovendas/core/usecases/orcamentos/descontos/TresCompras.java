package com.pucrs.modulovendas.core.usecases.orcamentos.descontos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.usecases.orcamentos.OrcamentoRepo;

@Service
public class TresCompras implements ICalculaDesconto {
    @Autowired
    private OrcamentoRepo or;

    public double calcula(String clienteName){
        long count = 0;
        List<Orcamento> orcs = or.findBynomeCliente(clienteName).stream().filter(i -> i.getEfetivado() == true).collect(Collectors.toList());

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
