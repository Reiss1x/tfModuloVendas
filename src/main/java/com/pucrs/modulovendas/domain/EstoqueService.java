package com.pucrs.modulovendas.domain;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.entities.Produto;
import com.pucrs.modulovendas.persistence.GalpaoRepository;
@Service
public class EstoqueService {
    
    @Autowired
    private GalpaoRepository gp;

    public Produto postProduto(Produto prod){
        gp.addProd(prod);
        return gp.getProd(prod.getCod());
    }

    public ArrayList<Produto> getProds(){
        return gp.getProds();
    }

}
