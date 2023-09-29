package com.outsider.catalogo.services;

import com.outsider.catalogo.domain.produto.Cor;
import com.outsider.catalogo.repository.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CorService {

    @Autowired
    public CorRepository repository;

    public Cor detalhar(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cor não encontrada!"));
    }

    public List<Cor> obterTodos() {

        return repository.findAll();
    }
}
