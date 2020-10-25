package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.AnimalDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    public AnimalDTO salvar(AnimalDTO dto){
        Animal entity = dto.toEntity();
        return AnimalDTO.create(animalRepository.save(entity));
    }

    public AnimalDTO editar(AnimalDTO dto,Integer id){
        Animal entity = animalRepository
                .findById(id)
                .map(animal -> {
                    animal.setTipoAnimal(dto.getTipoAnimal());
                    animal.setCliente(dto.getCliente());
                    animal.setNome(dto.getNome());
                    animal.setObservacao(dto.getObservacao());
                    animal.setId(dto.getId());
                    return animalRepository.save(animal);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
                return AnimalDTO.create(entity);
    }

    public void deletar(Integer id){
        Animal entity = animalRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST));
        animalRepository.delete(entity);
    }

    public List<AnimalDTO> FindAllAnimais(){
        return createDTOListAnimal(animalRepository.findAll());
    }
    public AnimalDTO findByIdAnimal(Integer id){
        Animal entity = animalRepository.findById(id).orElseThrow( () -> new RuntimeException("Erro"));
        return AnimalDTO.create(entity);
    }
    private List<AnimalDTO> createDTOListAnimal(List<Animal> animalList) {
        List<AnimalDTO> dtoList = new ArrayList<>();
        for (Animal c : animalList)
            dtoList.add(AnimalDTO.create(c));
        return dtoList;
    }
}
