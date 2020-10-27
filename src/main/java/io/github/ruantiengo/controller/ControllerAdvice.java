package io.github.ruantiengo.controller;


import io.github.ruantiengo.exception.ApiErrors;
import io.github.ruantiengo.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleIdNotFoundException(IdNotFoundException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
    @ExceptionHandler(RollbackException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRollBackException(RollbackException ex){
        return new ApiErrors(ex.getLocalizedMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleIllegalArgumentException(IllegalArgumentException ex){
        return new ApiErrors("Passado um argumento vazio");
    }
}