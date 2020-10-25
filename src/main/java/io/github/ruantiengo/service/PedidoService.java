package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.ItemPedidoDTO;
import io.github.ruantiengo.dto.PedidoDTO;
import io.github.ruantiengo.model.entity.*;
import io.github.ruantiengo.model.repository.ClienteRepository;
import io.github.ruantiengo.model.repository.ItemPedidoRepository;
import io.github.ruantiengo.model.repository.PedidoRepository;
import io.github.ruantiengo.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public Pedido save(PedidoDTO dto) throws Exception {
        Integer idCliente = dto.getCliente();
        Pedido pedido = new Pedido();
        // Cliente Salvo
        Cliente cliente = clienteRepository.findById(dto.getCliente()).orElseThrow( () -> new Exception());
        pedido.setCliente(cliente);

        // Outros saves
        pedido.setStatus(StatusPedido.PENDENTE);;

        // Lista de Produtos
        List<ItemPedido> list = converterItems(pedido, dto.getItens());
        pedido.setTotal(getTotalPedido(list));
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(list);
        pedido.setItens(list);
        return pedido;
    }

    public PedidoDTO findById(Integer id) throws Exception {
        Pedido entity = pedidoRepository.findById(id).orElseThrow( () -> new Exception("Cliente não encontrado"));
        return PedidoDTO.Create(entity);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow( () -> new RuntimeException("Erro"));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
    private Double getTotalPedido(List<ItemPedido> lista){
        Double total = 0.0;
        for (int i = 0; i < lista.size(); i++) {
            total = total + lista.get(i).getProduto().getPreco() * lista.get(i).getQuantidade();
        }
        return  total;
    }
}
