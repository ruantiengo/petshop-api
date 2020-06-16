package io.github.ruantiengo.dto;


import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.model.entity.Servico;
import io.github.ruantiengo.model.entity.ServicoPrestado;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoDTO {
    private Integer id;
    private String description;
    private Double valor;
    private List<Integer> servicos;
    private Integer idAnimal;
    private Integer idCliente;
    private String data;

    public static PedidoDTO create(Pedido pedido) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.createTypeMap(pedido, PedidoDTO.class).addMappings(mapper -> {
            mapper.map(src -> {
                List<Servico> servicoPrestados = pedido.getServicosPrestados();
                List<Integer> idList = new ArrayList<>();
                for (Servico s : servicoPrestados)
                    idList.add(s.getId());
                return idList;
            }, PedidoDTO::setServicos);
            mapper.map(src -> pedido.getAnimal().getId(), PedidoDTO::setIdAnimal);
            mapper.map(src -> pedido.getCliente().getId(), PedidoDTO::setIdCliente);
        }).map(pedido);
    }

    public Pedido toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Pedido.class);
    }

}
