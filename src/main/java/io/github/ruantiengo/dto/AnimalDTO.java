package io.github.ruantiengo.dto;

import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private Integer id;
    private String nome;
    private Integer cliente;
    private String tipoAnimal;
    private String observacao;

    public static AnimalDTO Create(Animal animal) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.createTypeMap(animal, AnimalDTO.class).addMappings(mapper -> {
            mapper.map(mapa -> {
                Cliente cliente = animal.getCliente();
                return cliente;
            }, AnimalDTO::setCliente);
            mapper.map(src -> animal.getCliente().getId(), AnimalDTO::setCliente);
        }).map(animal);
    }

    public Animal toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,Animal.class);
    }
}
