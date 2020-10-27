package io.github.ruantiengo.controller;

import io.github.ruantiengo.dto.ProdutoDTO;
import io.github.ruantiengo.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    public ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody @Valid ProdutoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
    @PutMapping("{id}")
    public ResponseEntity<ProdutoDTO> edit(@PathVariable Integer id, @RequestBody  @Valid
            ProdutoDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.edit(id,dto));
    }
}
