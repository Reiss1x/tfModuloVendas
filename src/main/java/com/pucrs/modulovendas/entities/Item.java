package com.pucrs.modulovendas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_cod")
    private Long cod;
    @Column(name = "cl_prodId")
    private int prodId;
    @Column(name = "cl_quantidade")
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
