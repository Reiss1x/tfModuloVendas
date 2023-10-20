package com.pucrs.modulovendas.persistence;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pucrs.modulovendas.domain.IGalpaoRepository;
import com.pucrs.modulovendas.entities.Produto;

@Repository
public class GalpaoRepository implements IGalpaoRepository {
    
    private ArrayList<Produto> listaProd = new ArrayList<Produto>();
    
    @Override
    public void addProd(Produto prod){
        listaProd.add(prod);
    }

    @Override
    public <T> Produto getProd(T prodId) {
        Optional<Produto> aux = listaProd.stream().filter(p -> p.getCod() == (int) prodId).findFirst();
        
        if(aux.isPresent()){
            return aux.get();
        } else { throw new IllegalArgumentException("Produto not found");}
    }

    @Override
    public ArrayList<Produto> getProds() {
        return listaProd;
    }

    
}
