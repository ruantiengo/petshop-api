package io.github.ruantiengo.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Integer id;
    private String nome;
    private Double preco;

    public static ProdutoDTO create(Produto produto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(produto,ProdutoDTO.class);
    }
    public Produto toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,Produto.class);
    }
}
