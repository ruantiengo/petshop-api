package io.github.ruantiengo.dto;


import io.github.ruantiengo.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

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
