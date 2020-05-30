package io.github.ruantiengo.controller;

import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.ClienteRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("cliente")
@Builder
@Data
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editar(@RequestBody Cliente clienteAtualizado,@PathVariable("id") Integer id){
        repository
                .findById(id)
                .map(cliente -> {
                    cliente.setCellphone(clienteAtualizado.getCellphone());
                    cliente.setEndereco(clienteAtualizado.getEndereco());
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setAnimalList(clienteAtualizado.getAnimalList());
                    repository.save(cliente);
                    return Void.TYPE;
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Cliente obterCliente(@PathVariable Integer id){
        return repository.findById(id)
                .map(cliente -> {
                    return cliente;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
    @GetMapping("{id}/animais")
    public List<Animal> obterAnimais(@PathVariable Integer id){
        return repository.findById(id)
                .map(cliente -> {
                    return cliente.getAnimalList();
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

}
