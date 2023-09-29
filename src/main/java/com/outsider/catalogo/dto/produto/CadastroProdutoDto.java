package com.outsider.catalogo.dto.produto;

import com.outsider.catalogo.domain.produto.Genero;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public record CadastroProdutoDto(
    @NotBlank
    String nome,
    @NotNull @Positive @Digits(integer = 8, fraction = 2)
    BigDecimal preco,
    @NotNull @DecimalMin(value = "0.01") @DecimalMax(value = "1.00") @Digits(integer = 1, fraction = 2)
    BigDecimal desconto,
    @NotNull
    Genero genero,
    @NotNull
    Long categoriaId,
    @NotNull
    Long subcategoriaId,
    @NotNull
    Long corId,
    @NotNull
    @Valid
    DadosTamanho dadosTamanho) {

}
