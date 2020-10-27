package io.github.ruantiengo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.ruantiengo.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Integer id;
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;
    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    private String telefone;
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;
    @NotEmpty(message = "{campo.endereco.obrigatorio}")
    private String endereco;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;



    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dataCadastro = cliente.getDataCadastro();
        this.telefone = cliente.getTelefone();
        this.endereco = cliente.getEndereco();
    }

    public Cliente toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Cliente.class);
    }

}
