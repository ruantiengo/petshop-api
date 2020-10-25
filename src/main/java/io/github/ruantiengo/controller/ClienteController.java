package io.github.ruantiengo.controller;

import io.github.ruantiengo.dto.AnimalDTO;
import io.github.ruantiengo.dto.ClienteDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.ClienteRepository;
import io.github.ruantiengo.service.ClienteService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteDTO> edit(@RequestBody ClienteDTO dto, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.editar(dto,id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        service.deletar(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findClienteById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClienteDTO>> listAllClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllClientes());
    }

    @GetMapping("{id}/animais")
    public ResponseEntity<List<AnimalDTO>> listAnimalsPorId(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.obterAnimais(id));
    }

}
