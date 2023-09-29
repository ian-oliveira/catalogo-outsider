package com.outsider.catalogo.infra.exception;

import com.outsider.catalogo.domain.exception.QuantidadeInsuficienteNoEstoqueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(QuantidadeInsuficienteNoEstoqueException.class)
    public ResponseEntity<String> tratarErroEstoqueInsuficiente(QuantidadeInsuficienteNoEstoqueException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> tratarErroEntidadeNotFound(EntityNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
