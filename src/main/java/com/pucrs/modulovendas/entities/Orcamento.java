package com.pucrs.modulovendas.entities;




public class Orcamento {
    private int id;
    private int data;
    private String nomeCliente;
    private Pedido pedido;
    private int somatorio;
    private Boolean efetivado;
    
    
    public Orcamento(int data, Pedido pedido, int somatorio) {
        this.id = pedido.getId();
        this.data = data;
        this.nomeCliente = pedido.getNomeCliente();
        this.pedido = pedido;
        this.somatorio = somatorio;
        this.efetivado = false;
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
