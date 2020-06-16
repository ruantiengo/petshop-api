package io.github.ruantiengo.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
    private String nome;
    private String cellphone;
    private String endereco;
    private Integer idAnimal;

}
