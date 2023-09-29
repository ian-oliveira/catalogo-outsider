package com.outsider.catalogo.domain.subcategoria;


import javax.persistence.*;

import com.outsider.catalogo.domain.categoria.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_subcategorias")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private Boolean ativo;

}
