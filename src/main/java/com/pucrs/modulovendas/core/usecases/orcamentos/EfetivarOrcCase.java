package com.pucrs.modulovendas.core.usecases.orcamentos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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

    public Orcamento execute(Long orcamentoId){
        Orcamento orc = getOrc.execute(orcamentoId);
        Pedido p = orc.getPedido();
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
        orc.setEfetivado();
        or.persist(orc);
        return orc;
    }
    
}
