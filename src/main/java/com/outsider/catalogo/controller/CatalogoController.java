package com.outsider.catalogo.controller;

import com.outsider.catalogo.dto.produto.DadosListagemProduto;
import com.outsider.catalogo.domain.produto.Genero;
import com.outsider.catalogo.domain.produto.Produto;
import com.outsider.catalogo.domain.produto.TamanhoLetra;
import com.outsider.catalogo.services.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService service;

    @GetMapping()
    public ResponseEntity<List<DadosListagemProduto>> filtrar(Pageable paginacao,
                                                              @RequestParam(value = "idCategoria", required = false) Long idCategoria,
                                                              @RequestParam(value = "idSubcategoria", required = false) Long idSubcategoria,
                                                              @RequestParam(value = "idCor", required = false) Long idCor,
                                                              @RequestParam(value = "genero", required = false) Genero genero,
                                                              @RequestParam(value = "tamanhoLetra", required = false) TamanhoLetra tamanhoLetra,
                                                              @RequestParam(value = "tamanhoNumero", required = false) Integer tamanhoNumero){

        Page<Produto> produtoPage = service.filtrarProdutos(paginacao, idCategoria, idSubcategoria, idCor, genero, tamanhoLetra, tamanhoNumero);

        List<DadosListagemProduto> dadosListagemProdutoList = new ArrayList<>();
        produtoPage.forEach(produto -> dadosListagemProdutoList.add(new DadosListagemProduto(produto)));

        return ResponseEntity.ok(dadosListagemProdutoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemProduto> detalhar(@PathVariable UUID id){

        Produto produto = service.detalhar(id);
        return ResponseEntity.ok(new DadosListagemProduto(produto));

    }

}
