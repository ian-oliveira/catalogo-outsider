package com.outsider.catalogo.repository.produto;

import com.outsider.catalogo.domain.produto.Genero;
import com.outsider.catalogo.domain.produto.Produto;
import com.outsider.catalogo.domain.produto.TamanhoLetra;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProdutoSpecification implements Specification<Produto> {

    private final Long idCategoria;
    private final Long idSubcategoria;
    private final Long idCor;
    private final Genero genero;
    private final TamanhoLetra tamanhoLetra;
    private final Integer tamanhoNumero;
    public ProdutoSpecification(Long idCategoria, Long idSubcategoria, Long idCor,
                                Genero genero, TamanhoLetra tamanhoLetra, Integer tamanhoNumero) {
        this.idCategoria = idCategoria;
        this.idSubcategoria = idSubcategoria;
        this.idCor = idCor;
        this.genero = genero;
        this.tamanhoLetra = tamanhoLetra;
        this.tamanhoNumero = tamanhoNumero;
    }


    @Override
    public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.isTrue(root.get("ativo")));

        if (idCategoria != null) {
            predicates.add(criteriaBuilder.equal(root.get("categoria").get("id"), idCategoria));
        }

        if (idSubcategoria != null) {
            predicates.add(criteriaBuilder.equal(root.get("subcategoria").get("id"), idSubcategoria));
        }

        if(idCor != null){
            predicates.add(criteriaBuilder.equal(root.get("cor").get("id"), idCor));
        }

        if (genero != null) {
            predicates.add(criteriaBuilder.equal(root.get("genero"), genero));
        }

        if (tamanhoLetra != null) {
            predicates.add(criteriaBuilder.equal(root.get("tamanho").get("tamanhoLetra"), tamanhoLetra));
        }

        if (tamanhoNumero != null) {
            predicates.add(criteriaBuilder.equal(root.get("tamanho").get("tamanhoNumero"), tamanhoNumero));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
