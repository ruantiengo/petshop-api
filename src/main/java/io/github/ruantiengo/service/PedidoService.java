package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.ItemPedidoDTO;
import io.github.ruantiengo.dto.PedidoDTO;
import io.github.ruantiengo.exception.IdNotFoundException;
import io.github.ruantiengo.model.entity.*;
import io.github.ruantiengo.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    AnimalRepository animalRepository;
    @Transactional
    public Pedido save(PedidoDTO dto) throws Exception {
        Integer idCliente = dto.getCliente();
        Pedido pedido = new Pedido();
        // Cliente Salvo
        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow( () -> new IdNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        // Animal Salvo
        Animal animal =  animalRepository.findById(dto.getAnimal())
                .orElseThrow( () -> new IdNotFoundException("Animal não encontrado"));
        pedido.setAnimal(animal);
        // Outros saves
        pedido.setStatus(StatusPedido.PENDENTE);
        // Lista de Produtos
        List<ItemPedido> list = converterItems(pedido, dto.getItens());
        pedido.setTotal(getTotalPedido(list));
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(list);
        pedido.setItens(list);
        return pedido;
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> getAll(){
        return makePedidoDTOList(pedidoRepository.findAll());
    }

    @Transactional
    public void delete(Integer id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow( () -> new IdNotFoundException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Integer id) throws Exception {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow( () -> new IdNotFoundException("Cliente não encontrado"));
        return PedidoDTO.Create(entity);
    }

    private List<PedidoDTO> makePedidoDTOList(List<Pedido> pedidoList){
        List<PedidoDTO> pedidoDTOList = new ArrayList<>();
        for (Pedido o: pedidoList) {
            pedidoDTOList.add(PedidoDTO.Create(o));
        }
        return pedidoDTOList;
    }
    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow( () -> new IdNotFoundException("Prduto não encontrado"));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
    private Double getTotalPedido(List<ItemPedido> lista){
        double total = 0.0;
        for (int i = 0; i < lista.size(); i++) {
            total = total + lista.get(i).getProduto().getPreco() * lista.get(i).getQuantidade();
        }
        return  total;
    }
}
