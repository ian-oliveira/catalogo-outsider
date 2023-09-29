package com.outsider.catalogo.controller;

import com.outsider.catalogo.dto.DadosAlteracaoEstoqueProduto;
import com.outsider.catalogo.dto.produto.DadosAtualizacaoProdutoDto;
import com.outsider.catalogo.dto.produto.DadosListagemProduto;
import com.outsider.catalogo.dto.produto.CadastroProdutoDto;
import com.outsider.catalogo.domain.produto.Produto;

import com.outsider.catalogo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody @Valid CadastroProdutoDto dto, UriComponentsBuilder uriBuilder){


        Produto produto = service.cadastrarProduto(dto);

        var uri =  uriBuilder.path("/catalogo/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemProduto(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProdutoDto dados){

        Produto produto = service.atualizar(dados);

        return ResponseEntity.ok(new DadosListagemProduto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable UUID id){

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/estoque")
    @Transactional
    public ResponseEntity alterarEstoque(@RequestBody @Valid DadosAlteracaoEstoqueProduto dadosAlteracaoEstoqueProduto){

        Produto produto = service.alterarEstoque(dadosAlteracaoEstoqueProduto);

        return ResponseEntity.ok(new DadosListagemProduto(produto));
    }
}
