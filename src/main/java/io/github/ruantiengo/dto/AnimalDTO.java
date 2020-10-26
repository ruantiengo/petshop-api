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

    public AnimalDTO(Animal animal){
        this.id = animal.getId();
        this.nome = animal.getNome();
        this.tipoAnimal = animal.getTipoAnimal();
        this.observacao = animal.getObservacao();
    }

    public Animal toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,Animal.class);
    }
}
