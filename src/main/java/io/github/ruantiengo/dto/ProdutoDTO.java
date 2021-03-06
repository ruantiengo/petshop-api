package io.github.ruantiengo.dto;


import io.github.ruantiengo.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Integer id;
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;
    @NotNull(message = "{campo.preco.obrigatorio}")
    private Double preco;

    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
    public Produto toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,Produto.class);
    }
}
