package io.github.ruantiengo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.ruantiengo.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String cellphone;
    private String endereco;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;



    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cellphone = cliente.getCellphone();
        this.endereco = cliente.getEndereco();
    }

    public Cliente toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Cliente.class);
    }

}
