package com.pucrs.modulovendas.core.domain;

/**
 * ProdutoNaoExisteException
 */
public class ProdutoNaoExisteException extends RuntimeException{
    public ProdutoNaoExisteException(String msg){
        super(msg);
    }
    
}