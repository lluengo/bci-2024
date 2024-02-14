package com.lluengo.bci.exception;

import com.lluengo.bci.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserFoundedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserFoundedException() {
        super("El usuario ya ha sido creado");
    }

    public UserFoundedException(User user) {
        super("El usuario " + user.getUsername() + " ya ha sido creado");
    }
}
