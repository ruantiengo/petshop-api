package io.github.ruantiengo.controller;

import io.github.ruantiengo.controller.dto.AnimalDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.AnimalRepository;
import io.github.ruantiengo.model.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
@CrossOrigin("*")
public class    AnimalController {


    private final AnimalRepository animalRepository;
    private final ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal salvar(@RequestBody AnimalDTO dto) {
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"O cliente não existe"));
        Animal animal = new Animal();
        animal.setCliente(cliente);
        animal.setObservacao(dto.getObservacao());
        animal.setNome(dto.getNome());
        animal.setTipoAnimal(dto.getTipoAnimal());
        return animalRepository.save(animal);
    }

    @GetMapping
    public List<Animal> obterTodos(){
        return animalRepository.findAll();
    }

    @GetMapping({"{idCliente}/animais"})
    public List<Animal> getAnimalbyIdCliente(@PathVariable Integer idCliente){
        return clienteRepository.findById(idCliente)
                .map(
                        cliente -> {
                            animalRepository.getAnimalsByCliente_Id(idCliente);
                                    return cliente.getAnimalList();
                        }
                ).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }


    @GetMapping("{id}")
    public Animal obterById(@PathVariable Integer id){
        return animalRepository.findById(id)
                .map(animal -> {
                    return animal;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("{id}")
    public void deletaAnimal(@PathVariable Integer id){
        animalRepository.findById(id)
                .map(animal-> {
                    animalRepository.delete(animal);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
    public void editar(@RequestBody Animal animalAtualizado,@PathVariable("id") Integer id){
        animalRepository
                .findById(id)
                .map(animal -> {
                    animal.setObservacao(animalAtualizado.getObservacao());
                    animal.setNome(animalAtualizado.getNome());
                    animal.setCliente(animalAtualizado.getCliente());
                    animalRepository.save(animal);
                    return Void.TYPE;
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

}   
