package com.outsider.catalogo.dto.subcategoria;

import com.outsider.catalogo.dto.categoria.DadosDetalhamentoCategoriaDto;
import com.outsider.catalogo.domain.subcategoria.Subcategoria;

public record DadosDetalhamentoSubcategoriaDto(String nome, DadosDetalhamentoCategoriaDto categ) {


    public DadosDetalhamentoSubcategoriaDto(Subcategoria subcategoria) {
        this(subcategoria.getNome(), new DadosDetalhamentoCategoriaDto(subcategoria.getCategoria()));
    }


}
