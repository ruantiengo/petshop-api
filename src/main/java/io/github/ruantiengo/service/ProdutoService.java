package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.ProdutoDTO;
import io.github.ruantiengo.model.entity.Produto;
import io.github.ruantiengo.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public ProdutoDTO save(ProdutoDTO dto){
        Produto entity = dto.toEntity();
        return ProdutoDTO.create(repository.save(entity));
    }
}
