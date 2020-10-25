package io.github.ruantiengo;

import io.github.ruantiengo.dto.ProdutoDTO;
import io.github.ruantiengo.model.entity.ItemPedido;
import io.github.ruantiengo.model.entity.Pedido;
import io.github.ruantiengo.model.entity.Produto;
import io.github.ruantiengo.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PetshopApplication {
    public static void main(String[] args) {

        SpringApplication.run(PetshopApplication.class,args);
    }
}
