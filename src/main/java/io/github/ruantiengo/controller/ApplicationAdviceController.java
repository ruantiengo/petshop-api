package io.github.ruantiengo.controller;

import io.github.ruantiengo.exception.ApiErrors;
import io.github.ruantiengo.exception.IdNotFoundException;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationAdviceController {
    @Getter
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiErrors handleIoNotFoundException(IdNotFoundException ex){
        String mensagem = ex.getLocalizedMessage();
        return new ApiErrors(mensagem);
    }
}
