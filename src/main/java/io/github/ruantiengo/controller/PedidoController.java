package io.github.ruantiengo.controller;


import io.github.ruantiengo.controller.dto.PedidoDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.model.repository.AnimalRepository;
import io.github.ruantiengo.model.repository.ClienteRepository;
import io.github.ruantiengo.model.repository.PedidoRepository;
import io.github.ruantiengo.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final AnimalRepository animalRepository;
    private final BigDecimalConverter converter;

    @PostMapping
    private Pedido salvar(@RequestBody PedidoDTO dto){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();
        Integer idAnimal = dto.getIdAnimal();
        Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST, "Cliente inexistente."));
        Animal animal =
                animalRepository
                    .findById(idAnimal)
                    .orElseThrow(()->
                            new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,"Animal não encontrado"));
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCadastro(data);
        pedido.setAnimal(animal);
        pedido.setValor(converter.converter(dto.getValor()));
        pedido.setDescription(dto.getDescription());
        return pedidoRepository.save(pedido);
    }

    @GetMapping
    List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }

    @GetMapping("/pesquisar")
    List<Pedido> pesquisar(
        @RequestParam(value = "nome", required = false, defaultValue = "") String nome
        ){
        return pedidoRepository.findByNome("%" + nome);
    }
}
