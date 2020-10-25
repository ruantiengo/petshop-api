package io.github.ruantiengo.controller;

import io.github.ruantiengo.dto.PedidoDTO;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public Pedido save(@RequestBody PedidoDTO dto) throws Exception {
        return service.save(dto);
    }
    @GetMapping("{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
}
