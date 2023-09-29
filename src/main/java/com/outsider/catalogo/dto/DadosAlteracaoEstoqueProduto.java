package com.outsider.catalogo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record DadosAlteracaoEstoqueProduto(@NotNull UUID id, @NotNull OperacaoEstoque operacao, @NotNull @Min(1) Integer qtd) {
}
