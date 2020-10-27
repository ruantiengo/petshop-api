package io.github.ruantiengo.dto;

import io.github.ruantiengo.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    @NotEmpty(message = "O usuario está vazio")
    private String username;
    @NotEmpty(message = "O campo senha está vazio.")
    private String senha;

    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.senha = usuario.getSenha();
    }
    public Usuario toEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,Usuario.class);
    }
}
