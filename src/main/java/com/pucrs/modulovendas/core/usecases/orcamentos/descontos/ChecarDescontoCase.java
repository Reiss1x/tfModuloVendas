package com.pucrs.modulovendas.core.usecases.orcamentos.descontos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChecarDescontoCase {
    @Autowired
    private CalculaDescontoCase descontoCalc;

    public double execute(String clienteName){
        return descontoCalc.calcula(clienteName);
    }
}
