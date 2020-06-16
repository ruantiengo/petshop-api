package io.github.ruantiengo.controller;


import io.github.ruantiengo.controller.dto.PedidoDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.entity.Servico;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.model.repository.AnimalRepository;
import io.github.ruantiengo.model.repository.ClienteRepository;
import io.github.ruantiengo.model.repository.PedidoRepository;
import io.github.ruantiengo.model.repository.ServicoRepository;
import io.github.ruantiengo.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/servico-prestado")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PedidoController {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final AnimalRepository animalRepository;
    private final BigDecimalConverter converter;
    private final ServicoRepository servicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Pedido salvar(@RequestBody PedidoDTO dto){

        Integer idCliente = dto.getIdCliente();
        Integer idAnimal = dto.getIdAnimal();
        Integer idServico = dto.getIdServico();
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
        Servico servico =
                servicoRepository
                .findById(idServico)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,"Serviço não encontrado"));

        Pedido servicoPrestado = new Pedido();
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setServico(servico);
        servicoPrestado.setAnimal(animal);
        servicoPrestado.setValorTotal(servico.getValor());
        servicoPrestado.setDescription(dto.getDescription());
        return pedidoRepository.save(servicoPrestado);
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
