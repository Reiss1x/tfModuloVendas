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
import com.pucrs.modulovendas.core.usecases.orcamentos.descontos.ChecarDescontoCase;



@Service
public class CriarOrcamentoCase {
    
    @Autowired
    private OrcamentoRepo or;
    @Autowired
    private GalpaoRepo gr;
    @Autowired
    private ChecarDescontoCase checarDesconto;
    @Autowired
    private SetValidadeCase setValidade;
    
    public Orcamento execute(Pedido ped){
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter fomatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = dataAtual.format(fomatador);
        
        double somatorio = 0;
        for(Item item : ped.getListaProd()){
            Optional<Produto> prods = gr.findAll().stream().filter(p -> p.getCod() == item.getProdId()).findFirst();
            if(prods.isPresent()){
                Produto prod = prods.get();
                somatorio += prod.getPreco() * item.getQuantidade();
            }
        }
        double desconto = checarDesconto.execute(ped.getNomeCliente());
        Orcamento orc = new Orcamento();
        orc.setData(data);
        orc.setDataLimite(setValidade.execute(data));
        orc.setSomatorio(somatorio);
        orc.setDesconto(desconto);
        orc.setPedido(ped);
        orc.setNomeCliente(ped.getNomeCliente());
        or.persist(orc);
        return orc;
    }
}
