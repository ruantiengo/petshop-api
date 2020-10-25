package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
