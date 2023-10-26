package com.pucrs.modulovendas.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_cod")
    private Long cod;
    @Column(name = "cl_name")
    private String name;
    
    
    @OneToMany
    @PrimaryKeyJoinColumn
    private List<Item> lista;
    protected Pedido(){}

    public String getNomeCliente() {
        return name;
    }
    public List<Item> getListaProd() {
        return lista;
    }
    @Override
    public String toString() {
        return "Pedido [name=" + name + ", lista=" + lista + "]";
    }
}
