package io.github.ruantiengo.service;

import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.ClienteRepository;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Builder
@Data
@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
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
    @Transactional
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    @Transactional
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    @Transactional
    public Cliente obterCliente(@PathVariable Integer id){
        return repository.findById(id)
                .map(cliente -> {
                    return cliente;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
    @GetMapping("{id}/animais")
    @Transactional
    public List<Animal> obterAnimais(@PathVariable Integer id){
        return repository.findById(id)
                .map(cliente -> {
                    return cliente.getAnimalList();
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

}
