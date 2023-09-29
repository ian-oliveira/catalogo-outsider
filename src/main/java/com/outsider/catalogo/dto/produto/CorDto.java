package com.outsider.catalogo.dto.produto;

import com.outsider.catalogo.domain.produto.Cor;

public record CorDto(String nome) {

    public CorDto(Cor cor){
        this(cor.getNome());
    }
}
