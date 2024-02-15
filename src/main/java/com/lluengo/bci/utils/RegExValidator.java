package com.lluengo.bci.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegExValidator  implements RegExInterface{

    @Value("${email.regex}")
    private String emailRegex;

    @Value("${password.regex}")
    private String passwordRegex;

    public boolean validateEmail(String email){
        return match(email,emailRegex);
    }

    public boolean validatePassword(String password){
        return match(password,passwordRegex);
    }

    @Override
    public boolean match(String field, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(field).matches();
    }
}
