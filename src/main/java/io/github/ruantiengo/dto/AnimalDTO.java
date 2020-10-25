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
    private Integer idCliente;
    private String tipoAnimal;
    private String observacao;
    private Cliente cliente;
    public static AnimalDTO create(Animal animal){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(animal,AnimalDTO.class);
    }
    public Animal toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,Animal.class);
    }
}
