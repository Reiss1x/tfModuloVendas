package com.pucrs.modulovendas.core.usecases.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucrs.modulovendas.core.domain.Item;
import com.pucrs.modulovendas.core.domain.ItemDTO;
import com.pucrs.modulovendas.core.domain.Pedido;
import com.pucrs.modulovendas.core.domain.PedidoDTO;
import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.domain.ProdutoNaoExisteException;
import com.pucrs.modulovendas.core.usecases.orcamentos.CriarOrcamentoCase;
import com.pucrs.modulovendas.core.usecases.orcamentos.GalpaoRepo;

import org.springframework.stereotype.Service;

@Service
public class CriarPedidoCase {
    
    @Autowired
    private PedidoRepo pr;
    @Autowired
    private CriarOrcamentoCase criarOrcamento;
    @Autowired
    private GalpaoRepo gr;
    
    public List<PedidoDTO> execute(List<PedidoDTO> ped){
        List<Produto> listaProdutos = gr.findAll();
        for(PedidoDTO pedDTO : ped){
            Pedido pedido = new Pedido(pedDTO.getName());
            List<Item> aux = new ArrayList<Item>();
            for (ItemDTO itens : pedDTO.getItems()){
                Optional<Produto> prod = listaProdutos.stream().filter(i -> i.getCod() == itens.getProdcod()).findAny();
                if(!prod.isPresent()){
                    throw new ProdutoNaoExisteException("Id de produto incorreto.");
                }
                Item item = new Item();    
                item.setProdcod(itens.getProdcod());
                item.setQuantidade(itens.getQuantidade());
                aux.add(item);
            }
            pedido.setLista(aux);
            pr.persist(pedido);
            criarOrcamento.execute(pedido);
        }
        return ped;
    }
}
//as