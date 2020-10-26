package io.github.ruantiengo.exception;
import lombok.Getter;

import java.util.List;
import java.util.Arrays;

public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }
}
