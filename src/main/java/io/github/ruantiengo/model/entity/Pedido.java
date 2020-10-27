package io.github.ruantiengo.model.entity;

import io.github.ruantiengo.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "animal_id", updatable = false)
    private Animal animal;

    @Column(name = "data_pedido", updatable = false)
    private LocalDate dataPedido;

    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @PrePersist
    public void PrePersist(){
        setDataPedido(LocalDate.now());
    }


}
