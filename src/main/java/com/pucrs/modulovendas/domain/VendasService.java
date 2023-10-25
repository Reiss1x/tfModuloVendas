package com.pucrs.modulovendas.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.entities.Item;
import com.pucrs.modulovendas.entities.Orcamento;
import com.pucrs.modulovendas.entities.Pedido;
import com.pucrs.modulovendas.entities.Produto;
import com.pucrs.modulovendas.persistence.IOrcamentoRepositoryJPA;

@Service
public class VendasService {
    
    @Autowired
    private IOrcamentoRepositoryJPA op;

    public Pedido postPedido(Pedido ped, List<Produto> produtos){
        postOrcamento(ped, 0, produtos);
        return ped;
    }

    public List<Pedido> getPedidos() {
        return op.findAllPedido();
    }

    public List<Orcamento> getOrcamentos() {
        return op.findAllOrcamento();
    }

    public Orcamento getOrcamento(Long orcamentoId) {
        if(op.findOrcamentoBycod(orcamentoId).isPresent()){
            return op.findOrcamentoBycod(orcamentoId).get();
        } else {
            throw new IllegalArgumentException("Orcamento: "+ orcamentoId +" não existe");
        }
    }

    public Orcamento postOrcamento(Pedido ped, int data,List<Produto> produtos){
        int somatorio = 0;
        for(Item item : ped.getListaProd()){
            Optional<Produto> aux = produtos.stream().filter(p -> p.getCod() == item.getProdId()).findFirst();
            if(aux.isPresent()){
                Produto prod = aux.get();
                somatorio += prod.getPreco();
            }
        }
        Orcamento orc = new Orcamento(data, ped, somatorio);
        op.save(orc);
        return orc;
    }

    public Orcamento efetivarOrcamento(Long orcamentoId, List<Produto> produtos){
        Orcamento orc = getOrcamento(orcamentoId);
        Pedido p = orc.getPedido();
        for (Item item : p.getListaProd()){
            Optional<Produto> aux = produtos.stream().filter(i -> i.getCod() == item.getProdId()).findFirst();
            if(aux.isPresent()){
                Produto prod = aux.get();
                if(!(prod.getQnt() >= item.getQuantidade())){
                throw new IllegalArgumentException("Produto: " + prod.getDesc()+ " sem estoque. Orçamento não efetivado.");
            }
            }
            
        }
        for (Item item : p.getListaProd()){
            Optional<Produto> aux = produtos.stream().filter(i -> i.getCod() == item.getProdId()).findFirst();
            if(aux.isPresent()){
                Produto prod = aux.get();
                prod.setQnt(prod.getQnt() - item.getQuantidade());
            }    
        }
        orc.setEfetivado();
        return orc;
    }

    public List<Orcamento> getRelatorio() {
        return op.findAllOrcamento().stream().filter(x -> x.getEfetivado() == true).collect(Collectors.toList());
    }
}
