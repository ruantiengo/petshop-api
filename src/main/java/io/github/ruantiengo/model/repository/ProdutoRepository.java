package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
    boolean existsByNome(String nome);
}
