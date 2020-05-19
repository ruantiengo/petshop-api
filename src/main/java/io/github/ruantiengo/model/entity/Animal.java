package io.github.ruantiengo.model.entity;

import io.github.ruantiengo.model.Enum.TipoAnimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    private String description;
    @Column(nullable = false)
    private TipoAnimal tipoAnimal;

    @ManyToOne
    @JoinColumn(name =  "id_cliente")
    private Cliente cliente;
}
