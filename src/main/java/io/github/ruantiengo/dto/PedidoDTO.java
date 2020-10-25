package io.github.ruantiengo.dto;
import io.github.ruantiengo.model.entity.ItemPedido;
import io.github.ruantiengo.model.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PedidoDTO {

    private Integer id;
    private Integer cliente;
    private double total;
    private List<ItemPedidoDTO> itens;

        public static PedidoDTO Create(Pedido pedido) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.createTypeMap(pedido, PedidoDTO.class).addMappings(mapper -> {
                mapper.map(mapa -> {
                    List<ItemPedido> itensPedidos = pedido.getItens();
                    List<String> produtoList = new ArrayList<>();
                    for (ItemPedido s : itensPedidos)
                        produtoList.add(s.getProduto().getNome());
                    return produtoList;
                }, PedidoDTO::setItens);;
                mapper.map(src -> pedido.getCliente().getId(), PedidoDTO::setCliente);
            }).map(pedido);
        }
    public Pedido toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,Pedido.class);
    }
}
