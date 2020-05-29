package io.github.ruantiengo.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalDTO {
    private String nome;
    private Integer idCliente;
    private String tipoAnimal;
    private String observacao;
}
