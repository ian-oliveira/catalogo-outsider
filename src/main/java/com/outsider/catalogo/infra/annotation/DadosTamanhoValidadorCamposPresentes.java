package com.outsider.catalogo.infra.annotation;

import com.outsider.catalogo.dto.produto.DadosTamanho;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DadosTamanhoValidadorCamposPresentes implements ConstraintValidator<ValidDadosTamanho, DadosTamanho> {
    @Override
    public boolean isValid(DadosTamanho dadosTamanho, ConstraintValidatorContext context) {

        return dadosTamanho.tamanhoLetra() != null || dadosTamanho.tamanhoNumero() != null;
    }

}
