package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.AnimalDTO;
import io.github.ruantiengo.exception.IdNotFoundException;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.AnimalRepository;
import io.github.ruantiengo.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    @Autowired
    public AnimalRepository animalRepository;
    @Autowired
    public ClienteRepository clienteRepository;

    @Transactional
    public AnimalDTO salvar( AnimalDTO dto){
        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow( () -> new IdNotFoundException("Cliente não encontrado"));
        Animal entity = dto.toEntity();
        entity.setCliente(cliente);
        animalRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    @Transactional
    public AnimalDTO editar(AnimalDTO dto,Integer id){
        Animal entity = animalRepository
                .findById(id)
                .map(animal -> {
                    animal.setTipoAnimal(dto.getTipoAnimal());
                    animal.setNome(dto.getNome());
                    animal.setObservacao(dto.getObservacao());
                    return animalRepository.save(animal);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
                return new AnimalDTO(entity);
    }
    @Transactional
    public void deletar(Integer id){
        Animal entity = animalRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST));
        animalRepository.delete(entity);
    }
    @Transactional(readOnly = true)
    public List<Animal> FindAllAnimais(){
        /*return createDTOListAnimal(animalRepository.findAll());*/
        // temporary solution
        return animalRepository.findAll();
    }
    @Transactional(readOnly = true)
    public AnimalDTO findByIdAnimal(Integer id){
        Animal entity = animalRepository.findById(id).orElseThrow( () -> new IdNotFoundException("Erro"));
        return new AnimalDTO(entity);
    }
    @Transactional(readOnly = true)
    private List<AnimalDTO> createDTOListAnimal(List<Animal> animalList) {
        List<AnimalDTO> dtoList = new ArrayList<>();
        for (Animal c : animalList)
            dtoList.add(new AnimalDTO(c));
        return dtoList;
    }
}
