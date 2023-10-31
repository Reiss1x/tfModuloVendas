package com.pucrs.modulovendas.core.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod;
    private String desc;
    private double preco;
    @Column(name = "cl_qntmin")
    private int qntMin;
    @Column(name = "cl_qntmax")
    private int qntMax;
    private int qnt;

    protected Produto(){
    }


    public Long getCod() {
        return cod;
    }
    public String getDesc() {
        return desc;
    }
    public double getPreco() {
        return preco;
    }

    public void setQntMin(int qntMin) {
        this.qntMin = qntMin;
    }

    public void setQntMax(int qntMax) {
        this.qntMax = qntMax;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public int getQntMin() {
        return qntMin;
    }

    public int getQntMax() {
        return qntMax;
    }

    public int getQnt() {
        return qnt;
    }

    @Override
    public String toString() {
        return "Produto [cod=" + cod + ", desc=" + desc + ", preco=" + preco + ", qntMin=" + qntMin + ", qntMax="
                + qntMax + ", qnt=" + qnt + "]";
    }
}
