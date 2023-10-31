package com.pucrs.modulovendas.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_cod")
    private Long cod;
    @Column(name = "cl_data")
    private String data;
    @Column(name = "cl_nomeCliente")
    private String nomeCliente;
    @OneToOne
    private Pedido pedido;
    @Column(name = "cl_somatorio")
    private double somatorio;
    @Column(name = "cl_efetivado")
    private Boolean efetivado = false;
    
    
    public Orcamento() {}


    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

    public void setData(String data) {
        this.data = data;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setSomatorio(int somatorio) {
        this.somatorio = somatorio;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public double getSomatorio() {
        return somatorio;
    }
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Orcamento [data=" + data + ", nomeCliente=" + nomeCliente + ", pedido=" + pedido
                + ", somatorio=" + somatorio + ", efetivado=" + efetivado + "]";
    }
}
