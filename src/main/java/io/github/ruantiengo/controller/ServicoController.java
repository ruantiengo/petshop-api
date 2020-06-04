package io.github.ruantiengo.controller;

import io.github.ruantiengo.controller.dto.ServicoDTO;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.entity.Servico;
import io.github.ruantiengo.model.repository.ServicoRepository;
import io.github.ruantiengo.util.BigDecimalConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@Data
@Builder
@CrossOrigin("*")
public class ServicoController {
    @Autowired
    private final ServicoRepository repository;
    private final BigDecimalConverter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvar(@RequestBody Servico servico){
        return repository.save(servico);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editar(@RequestBody Servico servicoAtualizado, @PathVariable("id") Integer id){
        repository
                .findById(id)
                .map(servico -> {
                    servico.setNome(servicoAtualizado.getNome());
                    servico.setDescricao(servicoAtualizado.getDescricao());
                    servico.setValor(servicoAtualizado.getValor());
                    repository.save(servico);
                    return Void.TYPE;
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        repository.findById(id)
                .map(servico -> {
                    repository.delete(servico);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
    @GetMapping
    public List<Servico> buscarTodos(){
        return repository.findAll();
    }
    @GetMapping("{id}")
    public Servico obterServicoById(@PathVariable Integer id){
        return repository.findById(id)
                .map(servico -> {
                    return servico;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
}
