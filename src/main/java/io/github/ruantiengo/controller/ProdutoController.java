package io.github.ruantiengo.controller;

import io.github.ruantiengo.dto.ProdutoDTO;
import io.github.ruantiengo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    public ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
    }
}
