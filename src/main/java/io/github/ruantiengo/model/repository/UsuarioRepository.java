package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UsuarioRepository extends JpaRepository<Usuario, Repository> {
    boolean existsByUsername(String username);
}
