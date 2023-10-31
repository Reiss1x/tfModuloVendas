package com.pucrs.modulovendas.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pucrs.modulovendas.core.domain.Produto;
import com.pucrs.modulovendas.core.usecases.produtos.CriarProdutoCase;
import com.pucrs.modulovendas.core.usecases.produtos.GetAllProdsCase;

@RestController
public class EstoqueController {
    
    @Autowired
    private CriarProdutoCase criarProduto;
    @Autowired
    private GetAllProdsCase getProds;

    //Criar produtos
    @PostMapping("/home/produtos/criar")
    public ResponseEntity<List<Produto>> postProds(@RequestBody List<Produto> produtos){
        return new ResponseEntity<List<Produto>>(criarProduto.execute(produtos), HttpStatus.OK);
    }

    //buscar produtos
    @GetMapping("/home/produtos")
    public ResponseEntity<List<Produto>> getProds(){
        return new ResponseEntity<List<Produto>>(getProds.execute(), HttpStatus.OK);
    }

    
}
