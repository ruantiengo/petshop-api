package io.github.ruantiengo.controller;


import io.github.ruantiengo.controller.dto.ServicoPrestadoDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.entity.ServicoPrestado;
import io.github.ruantiengo.model.repository.AnimalRepository;
import io.github.ruantiengo.model.repository.ClienteRepository;
import io.github.ruantiengo.model.repository.ServicoPrestadoRepository;
import io.github.ruantiengo.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/servico-prestado")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final AnimalRepository animalRepository;
    private final BigDecimalConverter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto){

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
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setCliente(cliente);

        servicoPrestado.setAnimal(animal);
        servicoPrestado.setValor(converter.converter(dto.getValor()));
        servicoPrestado.setDescription(dto.getDescription());
        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    List<ServicoPrestado> listarTodos(){
        return servicoPrestadoRepository.findAll();
    }

    @GetMapping("/pesquisar")
    List<ServicoPrestado> pesquisar(
        @RequestParam(value = "nome", required = false, defaultValue = "") String nome
        ){
        return servicoPrestadoRepository.findByNome("%" + nome);
    }
}
