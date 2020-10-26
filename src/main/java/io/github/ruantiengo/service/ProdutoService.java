package io.github.ruantiengo.service;

import io.github.ruantiengo.dto.ProdutoDTO;
import io.github.ruantiengo.exception.IdNotFoundException;
import io.github.ruantiengo.model.entity.Produto;
import io.github.ruantiengo.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public ProdutoDTO save(ProdutoDTO dto){
        Produto entity = dto.toEntity();
        return new ProdutoDTO(repository.save(entity));
    }

    public ProdutoDTO findById(Integer id){
        Produto produto = repository.findById(id).orElseThrow( () -> new IdNotFoundException("Não encontrado"));
        return new ProdutoDTO(produto);
    }

    public List<ProdutoDTO> findAll(){
        return createListProdutoDTO(repository.findAll());
    }

    @Transactional
    public void delete(Integer id){
        Produto produto = repository.findById(id).orElseThrow( () -> new IdNotFoundException("Produto não encontrado"));
        repository.delete(produto);
    }

    @Transactional
    public ProdutoDTO edit(Integer id,ProdutoDTO dto){
        Produto produto = repository.findById(id)
                .map(produtoAtualizado ->{
                    produtoAtualizado.setNome(dto.getNome());
                    produtoAtualizado.setPreco(dto.getPreco());
                    return repository.save(produtoAtualizado);
                        }).orElseThrow( () -> new IdNotFoundException("Produto não encontrado"));
        return new ProdutoDTO(produto);
    }

    private List<ProdutoDTO> createListProdutoDTO(List<Produto> produtoList){
        List<ProdutoDTO> produtoDTOList = new ArrayList<>();
        for (Produto produto: produtoList) {
            produtoDTOList.add(new ProdutoDTO(produto));
        }
        return produtoDTOList;
    }
}
