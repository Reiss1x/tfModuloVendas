package com.pucrs.modulovendas.entities;

public class Item {
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
