package com.pucrs.modulovendas.core.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_cod")
    private Long cod;
    @Column(name = "cl_name")
    private String name;
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<Item> lista;
    protected Pedido(){}
    public Pedido(String name){
        this.name = name;
    }
    
    

    public void setName(String name) {
        this.name = name;
    }
    public void setLista(List<Item> lista) {
        this.lista = lista;
    }
    public String getNomeCliente() {
        return name;
    }
    public List<Item> getListaProd() {
        return lista;
    }
    public Long getCod() {
        return cod;
    }
    @Override
    public String toString() {
        return "Pedido [name=" + name + ", lista=" + lista + "]";
    }
}
