package io.github.ruantiengo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoDTO {
    private String nome;
    private String descricao;
    private Double valor;
}
