package io.github.ruantiengo.service;

import io.github.ruantiengo.exception.UsuarioCadastradoException;
import io.github.ruantiengo.model.entity.Usuario;
import io.github.ruantiengo.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        boolean existe = repository.existsByUsername(usuario.getPassword());
        if(existe){
            throw new UsuarioCadastradoException();
        }
        return repository.save(usuario);
    }



}
