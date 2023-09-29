package com.outsider.catalogo.domain.exception;

public class QuantidadeInsuficienteNoEstoqueException extends RuntimeException{
    public QuantidadeInsuficienteNoEstoqueException(String msg) {
        super(msg);
    }
}
