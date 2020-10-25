package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.AnimalDTO;
import io.github.ruantiengo.dto.ClienteDTO;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.ClienteRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteDTO salvar(@RequestBody ClienteDTO dto) {
        Cliente entity = dto.toEntity();
        if(dto.getDataCadastro() == null) dto.setDataCadastro(LocalDate.now());
        return ClienteDTO.create(repository.save(entity));
    }

    public ClienteDTO editar(ClienteDTO dto, Integer id){
        Cliente clienteEditado = repository
                .findById(id)
                .map(cliente -> {
                    cliente.setCellphone(dto.getCellphone());
                    cliente.setEndereco(dto.getEndereco());
                    cliente.setNome(dto.getNome());
                    return repository.save(cliente);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return ClienteDTO.create(clienteEditado);
    }


    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public List<ClienteDTO> findAllClientes(){
        return createDTOList(repository.findAll());
    }


    public ClienteDTO findClienteById(@PathVariable Integer id){
        Cliente entity = repository.findById(id).orElseThrow( () -> new RuntimeException("Erro") );
        return ClienteDTO.create(entity);
    }

    public List<AnimalDTO> obterAnimais(@PathVariable Integer id){
         List<Animal> animalList = repository.findById(id)
                .map(cliente -> {
                    return cliente.getAnimalList();
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
         return createDTOListAnimal(animalList);
    }

    private List<ClienteDTO> createDTOList(List<Cliente> clienteList) {
        List<ClienteDTO> dtoList = new ArrayList<>();
        for (Cliente c : clienteList)
            dtoList.add(ClienteDTO.create(c));
        return dtoList;
    }
    private List<AnimalDTO> createDTOListAnimal(List<Animal> animalList) {
        List<AnimalDTO> dtoList = new ArrayList<>();
        for (Animal c : animalList)
            dtoList.add(AnimalDTO.create(c));
        return dtoList;
    }

}
