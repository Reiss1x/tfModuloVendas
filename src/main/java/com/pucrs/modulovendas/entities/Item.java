package com.pucrs.modulovendas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cod;
    private int prodId;
    private int quantidade;

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getProdId() {
        return prodId;
    }
    public int getQuantidade() {
        return quantidade;
    }
    @Override
    public String toString() {
        return "Item [prodId=" + prodId + ", quantidade=" + quantidade + "]";
    }
    
    
}
