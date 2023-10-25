package com.pucrs.modulovendas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cod;
    private int id;
    private int data;
    private String nomeCliente;
    private Pedido pedido;
    private int somatorio;
    private Boolean efetivado;
    
    
    public Orcamento(int data, Pedido pedido, int somatorio) {
        this.data = data;
        this.nomeCliente = pedido.getNomeCliente();
        this.pedido = pedido;
        this.somatorio = somatorio;
        this.efetivado = false;
    }

    protected Orcamento(){
        
    }

    public Boolean getEfetivado() {
        return efetivado;
    }

    public void setEfetivado() {
        this.efetivado = true;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Orcamento [data=" + data + ", nomeCliente=" + nomeCliente + ", pedido=" + pedido
                + ", somatorio=" + somatorio + ", efetivado=" + efetivado + "]";
    }
}
