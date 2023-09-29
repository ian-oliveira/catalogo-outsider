package com.outsider.catalogo.controller;


import com.outsider.catalogo.domain.subcategoria.Subcategoria;
import com.outsider.catalogo.dto.categoria.DadosDetalhamentoCategoriaDto;
import com.outsider.catalogo.domain.categoria.Categoria;
import com.outsider.catalogo.dto.subcategoria.DadosDetalhamentoSubcategoriaDto;
import com.outsider.catalogo.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategoriaDto> detalhar(@PathVariable Long id){

        Categoria categoria = service.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoCategoriaDto(categoria));

    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCategoriaDto>> listarTodos(Pageable paginacao){

        Page<Categoria> pageCategorias = service.obterTodos(paginacao);

        Page<DadosDetalhamentoCategoriaDto> pageDto = pageCategorias.map(DadosDetalhamentoCategoriaDto::new);

        return ResponseEntity.ok(pageDto);

    }


    @GetMapping("/subcateg/{id}")
    public ResponseEntity<List<DadosDetalhamentoSubcategoriaDto>> obterSubcategoriasRelacionadas(@PathVariable Long id){

        List<Subcategoria> listSubcateg = service.obterSubcategoriasRelacionadas(id);

        List<DadosDetalhamentoSubcategoriaDto> listDto = new ArrayList<DadosDetalhamentoSubcategoriaDto>();
        listSubcateg.forEach(subcategoria -> listDto.add(new DadosDetalhamentoSubcategoriaDto(subcategoria)));

        return ResponseEntity.ok(listDto);
    }

}
