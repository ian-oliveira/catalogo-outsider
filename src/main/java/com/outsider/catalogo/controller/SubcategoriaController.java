package com.outsider.catalogo.controller;


import com.outsider.catalogo.dto.subcategoria.DadosDetalhamentoSubcategoriaDto;
import com.outsider.catalogo.domain.subcategoria.Subcategoria;
import com.outsider.catalogo.services.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subcategoria")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoSubcategoriaDto> detalhar(@PathVariable Long id){

        Subcategoria subcategoria = service.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoSubcategoriaDto(subcategoria));

    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoSubcategoriaDto>> listarTodos(Pageable paginacao){

        Page<Subcategoria> pageSubcategorias = service.obterTodos(paginacao);

        Page<DadosDetalhamentoSubcategoriaDto> pageDto = pageSubcategorias.map(DadosDetalhamentoSubcategoriaDto::new);

        return ResponseEntity.ok(pageDto);

    }
}
