package io.github.ruantiengo.controller;

import io.github.ruantiengo.dto.TokenDTO;
import io.github.ruantiengo.dto.UsuarioDTO;
import io.github.ruantiengo.model.entity.Usuario;
import io.github.ruantiengo.model.repository.UsuarioRepository;
import io.github.ruantiengo.security.JwtService;
import io.github.ruantiengo.service.UsuarioService;
import io.github.ruantiengo.validation.SenhaInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private PasswordEncoder encoder;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid UsuarioDTO dto ){
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        dto.setSenha(senhaCriptografada);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(dto));
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody UsuarioDTO credenciais) {
        try {
            Usuario usuario = Usuario.builder()
                    .username(credenciais.getUsername())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getUsername(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
