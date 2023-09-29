package com.outsider.catalogo.services;

import com.outsider.catalogo.dto.DadosAlteracaoEstoqueProduto;
import com.outsider.catalogo.dto.produto.DadosAtualizacaoProdutoDto;
import com.outsider.catalogo.dto.produto.CadastroProdutoDto;
import com.outsider.catalogo.domain.categoria.Categoria;
import com.outsider.catalogo.domain.subcategoria.Subcategoria;
import com.outsider.catalogo.domain.produto.Cor;
import com.outsider.catalogo.domain.produto.Produto;
import com.outsider.catalogo.repository.CategoriaRepository;
import com.outsider.catalogo.repository.CorRepository;
import com.outsider.catalogo.repository.produto.ProdutoRepository;
import com.outsider.catalogo.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private SubcategoriaRepository subcategoriaRepository;
    @Autowired
    private CorRepository corRepository;

    public Produto cadastrarProduto(CadastroProdutoDto dto) {

        Subcategoria subcategoria = subcategoriaRepository.findByIdAndAtivoTrue(dto.subcategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Subcategoria não encontrada! Insira uma subcategoria válida..."));

        Categoria categoria = categoriaRepository.findByIdAndAtivoTrue(dto.categoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada! Insira uma Categoria válida..."));

        Cor cor = corRepository.findById(dto.corId())
                .orElseThrow(() -> new EntityNotFoundException("Cor não encontrada! Insira uma Cor válida..."));

        Produto produto = new Produto(dto,categoria, subcategoria, cor);

        produtoRepository.save(produto);

        return produto;
    }

    public Produto atualizar(DadosAtualizacaoProdutoDto dados) {

        Produto produto = produtoRepository.findByIdAndAtivoTrue(dados.idProduto())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));

        produto.atualizar(dados);

        return produto;
    }

    public void deletar(UUID idProduto) {

        Produto produto = produtoRepository.findByIdAndAtivoTrue(idProduto)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));

        produto.deletar();
    }


    public Produto alterarEstoque(DadosAlteracaoEstoqueProduto dadosAlteracaoEstoqueProduto) {

        Produto produto = produtoRepository.findByIdAndAtivoTrue(dadosAlteracaoEstoqueProduto.id())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));

        switch (dadosAlteracaoEstoqueProduto.operacao()) {

            case AUMENTAR -> produto.aumentarEstoque(dadosAlteracaoEstoqueProduto.qtd());

            case DIMINUIR -> produto.diminuirEstoque(dadosAlteracaoEstoqueProduto.qtd());
        }

        return produto;
    }

}
