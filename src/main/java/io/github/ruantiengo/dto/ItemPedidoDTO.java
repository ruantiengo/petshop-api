package io.github.ruantiengo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    @NotEmpty(message = "{campo.produto.obrigatorio}")
    private Integer produto;
    @NotEmpty(message = "{campo.quantidade.obrigatorio}")
    private Integer quantidade;
}
