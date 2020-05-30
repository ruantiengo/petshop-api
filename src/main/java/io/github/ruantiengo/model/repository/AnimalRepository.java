package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
    public List<Animal> getAnimalsByCliente_Id(Integer id);
}
