package com.outsider.catalogo.repository;

import com.outsider.catalogo.domain.subcategoria.Subcategoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
    Page<Subcategoria> findAllByAtivoTrue(Pageable paginacao);

    Optional<Subcategoria> findByIdAndAtivoTrue(Long id);

    List<Subcategoria> findAllByCategoriaIdAndAtivoTrue(Long id);
}
