package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.PedidoDTO;
import io.github.ruantiengo.dto.ServicoDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.model.entity.Servico;
import io.github.ruantiengo.model.repository.AnimalRepository;
import io.github.ruantiengo.model.repository.ClienteRepository;
import io.github.ruantiengo.model.repository.PedidoRepository;
import io.github.ruantiengo.model.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public PedidoDTO save(PedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente()).orElseThrow(() -> new IllegalArgumentException("Cliente invalido."));
        Animal animal = animalRepository.findById(dto.getIdAnimal()).orElseThrow(() -> new IllegalArgumentException("Animal invalido."));
        List<Integer> idList = dto.getServicos();
        List<Servico> servicoList = servicoRepository.findAllById(idList);
        Assert.isTrue(servicoList.size() == idList.size(), "Invalid service.");
        double valor = 0;
        for (Servico servico : servicoList)
            valor += servico.getValor();
        Pedido pedido = dto.toEntity();
        pedido.setValor(valor);
        pedido.setAnimal(animal);
        pedido.setCliente(cliente);
        pedido.setServicosPrestados(servicoList);
        return PedidoDTO.create(pedidoRepository.save(pedido));
    }

}
