package io.github.ruantiengo.dto;

import io.github.ruantiengo.model.entity.Animal;
import io.github.ruantiengo.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private Integer id;
    @NotEmpty(message = "campo.nome.obrigatorio")
    private String nome;
    @NotNull(message = "campo.cliente.obrigatorio")
    private Integer cliente;
    @NotEmpty(message = "campo.tipoAnimal.obrigatorio")
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
