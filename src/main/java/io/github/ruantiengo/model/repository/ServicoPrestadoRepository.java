package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado,Integer> {
    @Query("select s from ServicoPrestado s join s.cliente c" +
           " where upper (c.nome) like upper(:nome) ")
    List <ServicoPrestado> findByNome(@Param("nome") String nome);

}
