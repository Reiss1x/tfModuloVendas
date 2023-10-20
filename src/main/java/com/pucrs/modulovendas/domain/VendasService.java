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
import com.pucrs.modulovendas.persistence.OrcamentoRepository;

@Service
public class VendasService {
    
    @Autowired
    private OrcamentoRepository op;

    public Pedido postPedido(Pedido ped, List<Produto> produtos){
        postOrcamento(ped, 0, produtos);
        return ped;
    }

    public List<Pedido> getPedidos() {
        return op.getPedidos();
    }

    public List<Orcamento> getOrcamentos() {
        return op.getOrcamentos();
    }

    public Orcamento getOrcamento(int orcamentoId) {
        return op.getOrcamentoById(orcamentoId);
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
        op.postOrcamento(orc);
        return orc;
    }

    public Orcamento efetivarOrcamento(int orcamentoId, List<Produto> produtos){
        Orcamento orc = op.getOrcamentoById(orcamentoId);
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
        return op.getOrcamentos().stream().filter(x -> x.getEfetivado() == true).collect(Collectors.toList());
    }
}
