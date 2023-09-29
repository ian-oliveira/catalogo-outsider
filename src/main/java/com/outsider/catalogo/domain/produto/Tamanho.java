package com.outsider.catalogo.domain.produto;

import javax.persistence.*;

import com.outsider.catalogo.dto.produto.DadosTamanho;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tamanho {

    @Column(name = "tamanho_letra")
    @Enumerated(EnumType.STRING)
    private TamanhoLetra tamanhoLetra;

    @Column(name = "tamanho_nro")
    private Integer tamanhoNumero;


    public Tamanho(DadosTamanho dadosTamanho) {
        this.tamanhoLetra =  dadosTamanho.tamanhoLetra();
        this.tamanhoNumero = dadosTamanho.tamanhoNumero();
    }
}
