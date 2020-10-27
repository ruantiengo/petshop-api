package io.github.ruantiengo.service;


import io.github.ruantiengo.dto.TokenDTO;
import io.github.ruantiengo.dto.UsuarioDTO;
import io.github.ruantiengo.exception.IdNotFoundException;
import io.github.ruantiengo.model.entity.Usuario;
import io.github.ruantiengo.model.repository.UsuarioRepository;
import io.github.ruantiengo.validation.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDTO save(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario = dto.toEntity();
        usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    public UserDetails autenticar(Usuario usuarioDTO){
        UserDetails usuario = loadUserByUsername(usuarioDTO.getUsername());
        boolean isIqual = passwordEncoder.matches(usuarioDTO.getSenha(),usuario.getPassword());
        if(isIqual)
            return usuario;
        throw new SenhaInvalidaException("Senha incorreta.");
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IdNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
