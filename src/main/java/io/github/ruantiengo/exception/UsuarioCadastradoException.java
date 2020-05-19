package io.github.ruantiengo.exception;


public class UsuarioCadastradoException extends RuntimeException {
    public UsuarioCadastradoException() {
        super("Usuario já cadastrado");
    }
}
