package com.outsider.catalogo.dto.produto;

import com.outsider.catalogo.dto.subcategoria.DadosDetalhamentoSubcategoriaDto;
import com.outsider.catalogo.domain.produto.Genero;
import com.outsider.catalogo.domain.produto.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public record DadosListagemProduto(UUID id, String nome, BigDecimal preco, BigDecimal desconto, BigDecimal precoFinal,
                                   Integer qtdEmEstoque, Genero genero, CorDto cor, DadosTamanho dadosTamanho,
                                   DadosDetalhamentoSubcategoriaDto subcateg) {


    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDesconto(), produto.getPrecoFinal(),
                produto.getQuantidadeEstoque(), produto.getGenero(), new CorDto(produto.getCor()), new DadosTamanho(produto.getTamanho()),
                new DadosDetalhamentoSubcategoriaDto(produto.getSubcategoria()));
    }
}
