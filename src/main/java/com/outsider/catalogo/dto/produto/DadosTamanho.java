package com.outsider.catalogo.dto.produto;

import com.outsider.catalogo.infra.annotation.ValidDadosTamanho;
import com.outsider.catalogo.domain.produto.Tamanho;
import com.outsider.catalogo.domain.produto.TamanhoLetra;

import javax.validation.constraints.Positive;

@ValidDadosTamanho
public record DadosTamanho(TamanhoLetra tamanhoLetra, @Positive Integer tamanhoNumero) {
    public DadosTamanho(Tamanho tamanho) {
        this(tamanho.getTamanhoLetra(), tamanho.getTamanhoNumero());
    }
}
