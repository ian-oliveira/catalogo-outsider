package com.outsider.catalogo.controller;


import com.outsider.catalogo.domain.produto.Cor;
import com.outsider.catalogo.dto.produto.CorDto;
import com.outsider.catalogo.services.CorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cor")
public class CorController {

    @Autowired
    private CorService service;

    @GetMapping("/{id}")
    public ResponseEntity<CorDto> detalhar(@PathVariable Long id){

        Cor cor = service.detalhar(id);
        return ResponseEntity.ok(new CorDto(cor));

    }

    @GetMapping
    public ResponseEntity<List<CorDto>> listarTodos(){

        List<Cor> listCor = service.obterTodos();

        List<CorDto> listDto = new ArrayList<>();

        listCor.forEach(cor-> listDto.add(new CorDto(cor)));

        return ResponseEntity.ok(listDto);

    }

}
