package io.github.ruantiengo.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    @NotEmpty(message = "O nome é obrigatorio")
    private String nome;

    @Column(length = 15)
    private String cellphone;

    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(length = 100)
    private String endereco;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cliente")
    @JsonIgnore
    private List<Animal> animalList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidoList;


    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
