package com.lluengo.bci.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegExValidator  implements RegExInterface{

    private final static String EMAIL_RE = "^(.+)@(.+)$";
    private final static String PASSWORD_RE = "^(?=.*[a-z])(?=.*[A-Z]{1})(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,12}$";

    public boolean validateEmail(String email){
        return match(email,EMAIL_RE);
    }

    public boolean validatePassword(String password){
        return match(password,PASSWORD_RE);
    }

    @Override
    public boolean match(String field, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(field).matches();
    }
}
