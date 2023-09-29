package com.outsider.catalogo.services;

import com.outsider.catalogo.domain.subcategoria.Subcategoria;
import com.outsider.catalogo.repository.CategoriaRepository;
import com.outsider.catalogo.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SubcategoriaService {

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Page<Subcategoria> obterTodos(Pageable paginacao) {

        return subcategoriaRepository.findAllByAtivoTrue(paginacao);
    }

    public Subcategoria detalhar(Long id) {

        Optional<Subcategoria> opt = subcategoriaRepository.findByIdAndAtivoTrue(id);

        if (opt.isEmpty()) {
            throw new EntityNotFoundException("Subcategoria não encontrada!");
        }

        return opt.get();
    }
}
