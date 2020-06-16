package io.github.ruantiengo.controller;

import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.ClienteRepository;
import io.github.ruantiengo.service.ClienteService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClienteController {
    private ClienteService service;

}
