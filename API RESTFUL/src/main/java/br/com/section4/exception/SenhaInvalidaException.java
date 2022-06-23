package br.com.section4.exception;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException() {
        super("Senha inválida");
    }
}
