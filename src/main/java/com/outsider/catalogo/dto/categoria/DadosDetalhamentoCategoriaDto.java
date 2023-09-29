package com.outsider.catalogo.dto.categoria;

import com.outsider.catalogo.domain.categoria.Categoria;

public record DadosDetalhamentoCategoriaDto(String nome) {

    public DadosDetalhamentoCategoriaDto(Categoria categoria) {
        this(categoria.getNome());
    }
}
