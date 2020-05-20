package io.github.ruantiengo.controller;

import io.github.ruantiengo.controller.dto.AnimalDTO;
import io.github.ruantiengo.model.Enum.TipoAnimal;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.AnimalRepository;
import io.github.ruantiengo.model.repository.ClienteRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {


    private final AnimalRepository animalRepository;
    private final ClienteRepository clienteRepository;

    @PostMapping
    public Animal salvar(@RequestBody AnimalDTO dto) {
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"O cliente não existe"));
        Animal animal = new Animal();
        animal.setCliente(cliente);
        animal.setDescription(dto.getDescription());
        animal.setNome(dto.getNome());
        animal.setTipoAnimal(TipoAnimal.valueOf(dto.getTipoAnimal()));
        return animalRepository.save(animal);
    }
}   
