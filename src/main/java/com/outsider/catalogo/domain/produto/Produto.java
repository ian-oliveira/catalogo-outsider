package com.outsider.catalogo.domain.produto;

import com.outsider.catalogo.domain.exception.QuantidadeInsuficienteNoEstoqueException;
import com.outsider.catalogo.dto.produto.DadosAtualizacaoProdutoDto;
import com.outsider.catalogo.dto.produto.CadastroProdutoDto;
import com.outsider.catalogo.domain.categoria.Categoria;
import com.outsider.catalogo.domain.subcategoria.Subcategoria;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Produto {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String nome;

    private BigDecimal preco;

    private BigDecimal desconto;

    @Positive
    @Digits(integer = 10, fraction = 2)
    @NotNull
    @Column(name = "preco_final")
    private BigDecimal precoFinal;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "cor_id")
    private Cor cor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "subcategoria_id")
    private Subcategoria subcategoria;

    @Embedded
    private Tamanho tamanho;

    @Column(name = "qtd_em_estoque")
    private Integer quantidadeEstoque;

    @NotNull
    private Boolean ativo;

    public Produto(CadastroProdutoDto dto, Categoria categoria, Subcategoria subcategoria, Cor cor) {
        this.ativo = true;
        this.id = UUID.randomUUID();
        this.nome = dto.nome();
        this.preco = dto.preco();
        this.desconto = dto.desconto();
        this.precoFinal = calcularPrecoFinal(dto.preco(), dto.desconto());
        this.genero = dto.genero();
        this.cor = cor;
        this.categoria = categoria;
        this.subcategoria = subcategoria;

        this.tamanho = new Tamanho(dto.dadosTamanho());

    }

    private BigDecimal calcularPrecoFinal(BigDecimal preco, BigDecimal desconto){

        BigDecimal porcentagemDoValorInicial = new BigDecimal(1).subtract(desconto);

        return preco.multiply(porcentagemDoValorInicial).setScale(2, RoundingMode.DOWN);
    }

    public void atualizar(DadosAtualizacaoProdutoDto dados) {

        if(dados.nome() != null){
            this.nome = nome;
        }
        if(dados.preco() != null){
            this.preco = dados.preco();
        }
        if(dados.desconto() != null){
            this.desconto = dados.desconto();
        }

        this.precoFinal = calcularPrecoFinal(preco, desconto);
    }

    public void deletar() {
        this.ativo = false;
    }

    public void aumentarEstoque(Integer qtd) {

        if (quantidadeEstoque == null){
            this.quantidadeEstoque = qtd;
        } else {
            this.quantidadeEstoque = quantidadeEstoque + qtd;
        }
    }

    public void diminuirEstoque(Integer qtd) {

        if (quantidadeEstoque == null || quantidadeEstoque < qtd){
            throw new QuantidadeInsuficienteNoEstoqueException("Quantidade insuficiente de produtos no estoque");
        }

        this.quantidadeEstoque = quantidadeEstoque - qtd;
    }
}