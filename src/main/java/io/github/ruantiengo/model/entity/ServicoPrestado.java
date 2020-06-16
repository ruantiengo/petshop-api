package io.github.ruantiengo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "servicos_prestados")
@Data
public class ServicoPrestado {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servico")
    private Servico servico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

}
