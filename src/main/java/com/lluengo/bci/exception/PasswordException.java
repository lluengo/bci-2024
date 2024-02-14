package com.lluengo.bci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PasswordException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public PasswordException() {
        super("El password no cumple con el formato. El mismo debe poseer entre 8 y 12 caracteres. una sola letra mayuscula y dos numeros");
    }
}