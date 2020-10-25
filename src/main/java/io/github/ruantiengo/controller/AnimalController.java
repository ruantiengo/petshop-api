package io.github.ruantiengo.controller;

import io.github.ruantiengo.dto.AnimalDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> save(@RequestBody AnimalDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.salvar(dto));
    }
    @PutMapping("{id}")
    public ResponseEntity<AnimalDTO> edit(@RequestBody AnimalDTO dto,@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(animalService.editar(dto,id));
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        animalService.deletar(id);
    }

    @GetMapping("/all")
    public List<Animal> findAllAnimais(){ // Temporariamente retornando o proprio objeto
        return animalService.FindAllAnimais();
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimalDTO> findByIdAnimal(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findByIdAnimal(id));
    }

}   
