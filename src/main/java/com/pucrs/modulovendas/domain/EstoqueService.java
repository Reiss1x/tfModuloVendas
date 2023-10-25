package com.pucrs.modulovendas.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucrs.modulovendas.entities.Produto;
import com.pucrs.modulovendas.persistence.IGalpaoRepositoryJPA;
@Service
public class EstoqueService {
    
    @Autowired
    private IGalpaoRepositoryJPA gp;

    public Produto postProduto(Produto prod){
        gp.save(prod);
        return getProd(prod.getCod());
    }

    public List<Produto> getProds(){
        return gp.findAll();
    }

    public Produto getProd(Long cod){
        
        if(gp.findById(cod).isPresent()){
            return gp.findById(cod).get();
        } else {
            throw new IllegalArgumentException("Produto: "+ cod + " não existe.");
        }
    }

}
