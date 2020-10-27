package io.github.ruantiengo.controller;

import io.github.ruantiengo.dto.PedidoDTO;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public Pedido save(@RequestBody @Valid PedidoDTO dto) throws Exception {
        return service.save(dto);
    }
    @GetMapping("{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
