package com.pucrs.modulovendas.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.domain.EstoqueService;
import com.pucrs.modulovendas.entities.Produto;

@RestController
public class EstoqueController {
    
    @Autowired
    private EstoqueService es;

    //Criar produtos
    @PostMapping("/home/produtos/criar")
    public ResponseEntity<List<Produto>> postProds(@RequestBody List<Produto> produtos)
    {
        for(Produto p : produtos){
            es.postProduto(p);
        }
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    //buscar produtos
    @GetMapping("/home/produtos")
    public ResponseEntity<List<Produto>> getProds(){
        return new ResponseEntity<List<Produto>>(es.getProds(), HttpStatus.OK);
    }

    
}
