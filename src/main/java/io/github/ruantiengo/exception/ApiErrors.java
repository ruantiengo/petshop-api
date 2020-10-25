package io.github.ruantiengo.exception;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("CanBeFinal")
public class ApiErrors {
    public List<String> errors;
    public ApiErrors(String message){
        this.errors= Arrays.asList(message);
    }
}
