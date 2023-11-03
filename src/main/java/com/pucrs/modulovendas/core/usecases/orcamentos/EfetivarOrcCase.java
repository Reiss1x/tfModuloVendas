package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.ForaDeValidadeException;
import com.pucrs.modulovendas.core.domain.Item;
import com.pucrs.modulovendas.core.domain.Orcamento;
import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.domain.Produto;

import org.springframework.stereotype.Service;
@Service
public class EfetivarOrcCase {
    @Autowired
    private OrcamentoRepo or;
    @Autowired
    private GetOrcByCodCase getOrc;
    @Autowired
    private GalpaoRepo gr;

    public String execute(Long orcamentoId){
        Orcamento orc = getOrc.execute(orcamentoId);
        Pedido p = orc.getPedido();
        double precoFinal;
        if(!getValidade(orc.getData())){
            try {
                throw new ForaDeValidadeException("Data de validade para efetivação do orçamento excedida.");
            } catch (ForaDeValidadeException e) {
                e.printStackTrace();
            }
        }

        for (Item item : p.getListaProd()){
            Optional<Produto> aux = gr.findAll().stream().filter(i -> i.getCod() == item.getProdId()).findFirst();
            if(aux.isPresent()){
                Produto prod = aux.get();
                if(!(prod.getQnt() >= item.getQuantidade())){
                throw new IllegalArgumentException("Produto: " + prod.getDesc()+ " sem estoque. Orçamento não efetivado.");
            }
            }
            
        }

        for (Item item : p.getListaProd()){
            Optional<Produto> aux = gr.findAll().stream().filter(i -> i.getCod() == item.getProdId()).findFirst();
            if(aux.isPresent()){
                Produto prod = aux.get();
                prod.setQnt(prod.getQnt() - item.getQuantidade());
                gr.persist(prod);
            }    
        }

        precoFinal = orc.getSomatorio() - (orc.getSomatorio() * orc.getDesconto());
        precoFinal += precoFinal * orc.getImposto();
        orc.setPrecoFinal(precoFinal);
        orc.setEfetivado();
        or.persist(orc);
        return "Orcamento efetivado.";
    }

    public boolean getValidade(String dataFinal){
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataValidade = LocalDate.parse(dataFinal, dateFormatter);

        return dataValidade.isBefore(dataAtual);
    }
    
}
