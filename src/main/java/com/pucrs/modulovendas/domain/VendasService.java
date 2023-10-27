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
import com.pucrs.modulovendas.persistence.IGalpaoRepositoryJPA;
import com.pucrs.modulovendas.persistence.IOrcamentoRepositoryJPA;
import com.pucrs.modulovendas.persistence.IPedidosRepositoryJPA;

@Service
public class VendasService {
    
    @Autowired
    private IOrcamentoRepositoryJPA or;
    @Autowired
    private IPedidosRepositoryJPA pr;
    @Autowired 
    private IGalpaoRepositoryJPA gr;

    public Pedido postPedido(Pedido ped){
        postOrcamento(ped, 0);
        return ped;
    }

    public List<Pedido> getPedidos() {
        return pr.findAll();
    }

    public List<Orcamento> getOrcamentos() {
        return or.findAll();
    }

    public Orcamento getOrcamento(Long orcamentoId) {
        if(or.findBycod(orcamentoId).isPresent()){
            return or.findBycod(orcamentoId).get();
        } else {
            throw new IllegalArgumentException("Orcamento: "+ orcamentoId +" não existe");
        }
    }

    public Orcamento postOrcamento(Pedido ped, int data){
        int somatorio = 0;
        for(Item item : ped.getListaProd()){
            Optional<Produto> aux = gr.findAll().stream().filter(p -> p.getCod() == item.getProdId()).findFirst();
            if(aux.isPresent()){
                Produto prod = aux.get();
                somatorio += prod.getPreco();
            }
        }
        Orcamento orc = new Orcamento(data, somatorio);
        orc.setPedido(ped);
        or.save(orc);
        return orc;
    }

    public Orcamento efetivarOrcamento(Long orcamentoId){
        Orcamento orc = getOrcamento(orcamentoId);
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
            }    
        }
        orc.setEfetivado();
        return orc;
    }

    public List<Orcamento> getRelatorio() {
        return or.findAll().stream().filter(x -> x.getEfetivado() == true).collect(Collectors.toList());
    }
}
