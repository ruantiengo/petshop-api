package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    @Query("select s from Pedido s join s.cliente c" +
           " where upper (c.nome) like upper(:nome) ")
    List <Pedido> findByNome(@Param("nome") String nome);

}
