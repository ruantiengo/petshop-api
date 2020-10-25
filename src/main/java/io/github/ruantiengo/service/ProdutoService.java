package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.ProdutoDTO;
import io.github.ruantiengo.exception.IdNotFoundException;
import io.github.ruantiengo.model.entity.Produto;
import io.github.ruantiengo.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public ProdutoDTO save(ProdutoDTO dto){
        Produto entity = dto.toEntity();
        return ProdutoDTO.create(repository.save(entity));
    }

    public ProdutoDTO findById(Integer id){
        Produto produto = repository.findById(id).orElseThrow( () -> new IdNotFoundException("Não encontrado"));
        return ProdutoDTO.create(produto);
    }

    public List<ProdutoDTO> findAll(){
        return createListProdutoDTO(repository.findAll());
    }

    public void delete(Integer id){
        Produto produto = repository.findById(id).orElseThrow( () -> new IdNotFoundException("Produto não encontrado"));
        repository.delete(produto);
    }

    public ProdutoDTO edit(Integer id,ProdutoDTO dto){
        Produto produto = repository.findById(id)
                .map(produtoAtualizado ->{
                    produtoAtualizado.setNome(dto.getNome());
                    produtoAtualizado.setPreco(dto.getPreco());
                    return repository.save(produtoAtualizado);
                        }).orElseThrow( () -> new IdNotFoundException("Produto não encontrado"));
        return ProdutoDTO.create(produto);
    }

    private List<ProdutoDTO> createListProdutoDTO(List<Produto> produtoList){
        List<ProdutoDTO> produtoDTOList = new ArrayList<>();
        for (Produto produto: produtoList) {
            produtoDTOList.add(ProdutoDTO.create(produto));
        }
        return produtoDTOList;
    }
}
