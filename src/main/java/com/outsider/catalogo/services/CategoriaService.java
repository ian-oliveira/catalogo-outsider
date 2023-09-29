package com.outsider.catalogo.services;

import com.outsider.catalogo.domain.categoria.Categoria;
import com.outsider.catalogo.domain.subcategoria.Subcategoria;
import com.outsider.catalogo.repository.CategoriaRepository;
import com.outsider.catalogo.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;


    public Page<Categoria> obterTodos(Pageable paginacao) {

        Page<Categoria> categorias = categoriaRepository.findAllByAtivoTrue(paginacao);
        return categorias;
    }

    public Categoria detalhar(Long id) {

        Optional<Categoria> opt = categoriaRepository.findByIdAndAtivoTrue(id);

        if (opt.isEmpty()){
            throw new EntityNotFoundException("Categoria não encontrada!");
        }

        return opt.get();
    }

    public List<Subcategoria> obterSubcategoriasRelacionadas(Long id) {

        return subcategoriaRepository.findAllByCategoriaIdAndAtivoTrue(id);
    }
}
