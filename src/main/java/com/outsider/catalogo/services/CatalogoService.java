package com.outsider.catalogo.services;

import com.outsider.catalogo.domain.produto.Genero;
import com.outsider.catalogo.domain.produto.Produto;
import com.outsider.catalogo.domain.produto.TamanhoLetra;
import com.outsider.catalogo.repository.produto.ProdutoRepository;
import com.outsider.catalogo.repository.produto.ProdutoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> filtrarProdutos(Pageable paginacao, Long idCategoria, Long idSubcategoria, Long idCor, Genero genero, TamanhoLetra tamanhoLetra, Integer tamanhoNumero) {

        ProdutoSpecification produtoSpecification = new ProdutoSpecification(idCategoria, idSubcategoria, idCor,
                genero, tamanhoLetra, tamanhoNumero);

       return produtoRepository.findAll(produtoSpecification, paginacao);

    }

    public Produto detalhar(UUID id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if(produtoOpt.isEmpty()){
            throw new EntityNotFoundException("Produto não encontrado!");
        }
        return produtoOpt.get();
    }
}

