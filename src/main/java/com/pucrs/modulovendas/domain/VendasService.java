package com.pucrs.modulovendas.domain;

import java.util.List;
import java.util.Optional;

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

    
}
