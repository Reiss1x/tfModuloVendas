package com.pucrs.modulovendas.domain;

import java.util.List;

import com.pucrs.modulovendas.entities.Produto;

public interface IGalpaoRepository {
    public <T> Produto getProd(T prodId);
    public List<Produto> getProds();
    public void addProd(Produto prod);
}
