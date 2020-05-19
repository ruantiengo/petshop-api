package io.github.ruantiengo.model.repository;

import io.github.ruantiengo.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
}
