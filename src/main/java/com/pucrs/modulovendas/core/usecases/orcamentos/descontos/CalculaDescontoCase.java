package com.pucrs.modulovendas.core.usecases.orcamentos.descontos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CalculaDescontoCase {
    @Autowired
    private TresCompras tres;
    @Autowired
    private DezCompras dez;

    public double calcula(String clienteName){
        double desconto = 0;
        


        List<ICalculaDesconto> calculadoras = new ArrayList<ICalculaDesconto>();
        calculadoras.add(dez);
        calculadoras.add(tres);
        for(ICalculaDesconto c : calculadoras){
            if (c.calcula(clienteName) > desconto){
            desconto = c.calcula(clienteName);
            }
        }
        return desconto;
    }
}
