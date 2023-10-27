package com.pucrs.modulovendas.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.entities.Item;
import com.pucrs.modulovendas.entities.ItemDTO;
import com.pucrs.modulovendas.entities.Orcamento;
import com.pucrs.modulovendas.entities.Pedido;
import com.pucrs.modulovendas.entities.PedidoDTO;
import com.pucrs.modulovendas.entities.Produto;
import com.pucrs.modulovendas.persistence.IGalpaoRepositoryJPA;
import com.pucrs.modulovendas.persistence.IOrcamentoRepositoryJPA;
import com.pucrs.modulovendas.persistence.IPedidosRepositoryJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class VendasService {
    
    @Autowired
    private IOrcamentoRepositoryJPA or;
    @Autowired
    private IPedidosRepositoryJPA pr;
    @Autowired 
    private IGalpaoRepositoryJPA gr;
    @PersistenceContext
    private EntityManager entityManager;

    public void postPedido(PedidoDTO ped){
        Pedido pedido = new Pedido(ped.getName());
        List<Item> aux = new ArrayList<Item>();
        for (ItemDTO itens : ped.getItems()){
            Item item = new Item();
            item.setProdcod(itens.getProdcod());
            item.setQuantidade(itens.getQuantidade());
            aux.add(item);
        }
        pedido.setLista(aux);
        pr.save(pedido);
        postOrcamento(pedido, 0);
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
                gr.save(prod);
            }    
        }
        orc.setEfetivado();
        or.save(orc);
        return orc;
    }

    public List<Orcamento> getRelatorio() {
        return or.findAll().stream().filter(x -> x.getEfetivado() == true).collect(Collectors.toList());
    }
}
