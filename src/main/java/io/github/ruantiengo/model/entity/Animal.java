package io.github.ruantiengo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String observacao;

    @Column
    private String tipoAnimal;

    @ManyToOne
    @JoinColumn(name =  "id_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "animal")
    @JsonIgnore
    private List<Pedido> pedidoList;
}
