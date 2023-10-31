package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.core.domain.Item;
import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.domain.Produto;



@Service
public class CriarOrcamentoCase {
    
    @Autowired
    private OrcamentoRepo or;
    @Autowired
    private GalpaoRepo gr;

    public Orcamento execute(Pedido ped){
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter fomatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = dataAtual.format(fomatador);
        
        int somatorio = 0;
        for(Item item : ped.getListaProd()){
            Optional<Produto> prods = gr.findAll().stream().filter(p -> p.getCod() == item.getProdId()).findFirst();
            if(prods.isPresent()){
                Produto prod = prods.get();
                somatorio += prod.getPreco();
            }
        }
        Orcamento orc = new Orcamento();
        orc.setData(data);
        orc.setSomatorio(somatorio);
        orc.setPedido(ped);
        orc.setNomeCliente(ped.getNomeCliente());
        or.persist(orc);
        return orc;
    }
}
