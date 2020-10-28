package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    boolean existsByNomeAndTelefone(String nome,String telefone);
}
