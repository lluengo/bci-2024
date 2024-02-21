package com.lluengo.bci.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {
    public String getToken(UserDetails user);
    public String getUsernameFromToken(String token);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver);

}
