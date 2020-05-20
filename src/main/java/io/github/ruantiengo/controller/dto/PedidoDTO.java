package io.github.ruantiengo.controller.dto;


import io.github.ruantiengo.model.entity.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PedidoDTO {
    private String description;
    private String valor;
    private Integer idAnimal;
    private Integer idCliente;
    private String data;
}
