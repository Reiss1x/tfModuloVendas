package com.pucrs.modulovendas.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.domain.EstoqueService;
import com.pucrs.modulovendas.entities.Produto;

@RestController
public class LojaController {
    
    @Autowired
    private EstoqueService es;

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
}
