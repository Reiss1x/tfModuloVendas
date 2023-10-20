package com.pucrs.modulovendas.entities;

import java.util.ArrayList;

public class Pedido {
    private int id;
    private String name;
    private ArrayList<Item> lista;
    
    
    public Pedido(int id, String name, ArrayList<Item> lista) {
        this.id = id;
        this.name = name;
        this.lista = lista;
    }
    public String getNomeCliente() {
        return name;
    }
    public ArrayList<Item> getListaProd() {
        return lista;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Pedido [id=" + id + ", name=" + name + ", lista=" + lista + "]";
    }
}
