package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.AnimalDTO;
import io.github.ruantiengo.dto.ClienteDTO;
import io.github.ruantiengo.exception.IdNotFoundException;
import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import io.github.ruantiengo.model.repository.ClienteRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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


    @Transactional
    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente entity = dto.toEntity();
        dto.setDataCadastro(entity.getDataCadastro());
        return new ClienteDTO(repository.save(entity));
    }

    @Transactional
    public ClienteDTO editar(ClienteDTO dto, Integer id){
            Cliente clienteEditado = repository
                    .findById(id)
                    .map(cliente -> {
                        cliente.setCellphone(dto.getCellphone());
                        cliente.setEndereco(dto.getEndereco());
                        cliente.setCpf(dto.getCpf());
                        cliente.setNome(dto.getNome());
                    return repository.save(cliente);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return new ClienteDTO();
    }

    @Transactional
    public void deletar(Integer id){
        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAllClientes(){
        return createDTOList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public ClienteDTO findClienteById(Integer id){
        Cliente entity = repository.findById(id).orElseThrow( () -> new IdNotFoundException("Cliente não encontrado ") );
        return new ClienteDTO(entity);
    }
    @Transactional(readOnly = true)
    public List<AnimalDTO> obterAnimais(Integer id){
         List<Animal> animalList = repository.findById(id)
                .map(cliente -> {
                    return cliente.getAnimalList();
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT));
         return createDTOListAnimal(animalList);
    }

    @Transactional(readOnly = true)
    private List<ClienteDTO> createDTOList(List<Cliente> clienteList) {
        List<ClienteDTO> dtoList = new ArrayList<>();
        for (Cliente c : clienteList)
            dtoList.add(new ClienteDTO(c));
        return dtoList;
    }
    @Transactional(readOnly = true)
    private List<AnimalDTO> createDTOListAnimal(List<Animal> animalList) {
        List<AnimalDTO> dtoList = new ArrayList<>();
        for (Animal c : animalList)
            dtoList.add(new AnimalDTO(c));
        return dtoList;
    }
}
