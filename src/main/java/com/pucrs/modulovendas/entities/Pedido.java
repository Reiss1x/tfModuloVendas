package com.pucrs.modulovendas.entities;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cod;
    private String name;
    private ArrayList<Item> lista;
    
    
    public Pedido(String name, ArrayList<Item> lista) {
        this.name = name;
        this.lista = lista;
    }
    public String getNomeCliente() {
        return name;
    }
    public ArrayList<Item> getListaProd() {
        return lista;
    }
    @Override
    public String toString() {
        return "Pedido [name=" + name + ", lista=" + lista + "]";
    }
}
