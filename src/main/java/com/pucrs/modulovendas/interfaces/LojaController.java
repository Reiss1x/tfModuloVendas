package com.pucrs.modulovendas.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.domain.EstoqueService;
import com.pucrs.modulovendas.domain.VendasService;
import com.pucrs.modulovendas.entities.Pedido;
import com.pucrs.modulovendas.entities.Produto;

@RestController
public class LojaController {
    
    @Autowired
    private EstoqueService es;
    @Autowired
    private VendasService vs;

    @GetMapping
    public String home(){
        return "Bem-Vindo a loja.";
    }
    
    //Criar produtos
    @PostMapping("/home/produtos/criar")
    public void postProds(@RequestBody List<Produto> produtos)
    {
        for(Produto p : produtos){
            es.postProduto(p);
        }
    }

    //buscar produtos
    @GetMapping("/home/produtos")
    public List<Produto> getProds(){
        return es.getProds();
    }

    //Criar pedidos
    @PostMapping("/home/pedidos/criar")
    public List<Pedido> postPedido(@RequestBody List<Pedido> pedidos){
        List<Produto> produtosEstoque = es.getProds();
        for(Pedido p : pedidos){
            vs.postPedido(p, produtosEstoque);
        }
        return pedidos;
    }
    
    //visualizar pedidos
    @GetMapping("/admin/pedidos")
    public List<Pedido> getPedidos(){
        return vs.getPedidos();
    }
}
