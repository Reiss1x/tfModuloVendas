package com.pucrs.modulovendas.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cod;
    private String desc;
    private int preco;
    private int qntMin;
    private int qntMax;
    private int qnt;

    protected Produto(Long cod, String desc, int preco, int qntMin, int qntMax, int qnt){
        this.cod = cod;
        this.desc = desc;
        this.preco = preco;
        this.qntMin = qntMin;
        this.qntMax = qntMax;
        this.qnt = qnt;
    }


    public Long getCod() {
        return cod;
    }
    public String getDesc() {
        return desc;
    }
    public int getPreco() {
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
