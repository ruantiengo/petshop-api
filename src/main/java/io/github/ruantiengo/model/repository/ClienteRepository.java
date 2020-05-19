package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
