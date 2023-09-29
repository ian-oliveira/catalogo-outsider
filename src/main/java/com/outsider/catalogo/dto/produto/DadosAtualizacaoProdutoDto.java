package com.outsider.catalogo.dto.produto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public record DadosAtualizacaoProdutoDto(@NotNull UUID idProduto, String nome, @NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal preco, @NotNull @DecimalMin(value = "0.01") @DecimalMax(value = "1.00") @Digits(integer = 3, fraction = 2) BigDecimal desconto) {
}
