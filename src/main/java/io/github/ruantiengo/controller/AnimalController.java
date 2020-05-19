package io.github.ruantiengo.controller;

import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.repository.AnimalRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {


    private final AnimalRepository repository;

    @PostMapping
    public Animal salvar(@RequestBody Animal animal) {
        return repository.save(animal);
    }
}   
