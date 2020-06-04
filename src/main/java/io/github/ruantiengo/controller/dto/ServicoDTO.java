package io.github.ruantiengo.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ServicoDTO {
    private String nome;
    private String descricao;
    private String valor;
}
