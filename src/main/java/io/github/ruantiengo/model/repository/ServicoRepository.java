package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico,Integer> {
}
