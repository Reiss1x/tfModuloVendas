package com.pucrs.modulovendas.core.usecases.pedidos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Item;
import com.pucrs.modulovendas.core.domain.ItemDTO;
import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.domain.PedidoDTO;
import com.pucrs.modulovendas.core.usecases.orcamentos.CriarOrcamentoCase;

public class CriarPedidoCase {
    
    @Autowired
    private PedidoRepo pr;
    @Autowired
    private CriarOrcamentoCase criarOrcamento;
    
    public void execute(PedidoDTO ped){
        Pedido pedido = new Pedido(ped.getName());
        List<Item> aux = new ArrayList<Item>();
        for (ItemDTO itens : ped.getItems()){
            Item item = new Item();
            item.setProdcod(itens.getProdcod());
            item.setQuantidade(itens.getQuantidade());
            aux.add(item);
        }
        pedido.setLista(aux);
        pr.persist(pedido);
        criarOrcamento.execute(pedido, 0);
    }
}
