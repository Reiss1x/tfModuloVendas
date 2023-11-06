package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
@Service
public class SetValidadeCase {
    
    public String execute(String dataStr){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataStr, dateFormatter);

        int mes = data.getMonthValue();
        int diasDeValidade = 0;

        if (mes == Month.JULY.getValue() || mes == Month.DECEMBER.getValue() || mes == Month.JANUARY.getValue() || mes == Month.FEBRUARY.getValue()) {
            diasDeValidade = 35;
        } else {
            diasDeValidade = 21;
        }

        LocalDate dataLimite = data.plusDays(diasDeValidade);
        return dataLimite.format(dateFormatter);
    }
}

