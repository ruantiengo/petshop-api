package io.github.ruantiengo.controller;

import io.github.ruantiengo.exception.UsuarioCadastradoException;
import io.github.ruantiengo.model.entity.Usuario;
import io.github.ruantiengo.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Usuario usuario){
            boolean existe = repository.existsByUsername(usuario.getUsername());
            if(!existe) {
                repository.save(usuario);
            }
            else{
                throw new UsuarioCadastradoException();
            }
    }
}
