package com.lluengo.bci.service.impl;

import com.lluengo.bci.entity.dto.auth.AuthResponseDto;
import com.lluengo.bci.entity.dto.auth.LoginRequestDto;
import com.lluengo.bci.entity.dto.auth.RegisterRequestDto;
import com.lluengo.bci.entity.mapper.RegisterRequestToUserMapper;
import com.lluengo.bci.exception.EmailValidationException;
import com.lluengo.bci.exception.PasswordException;
import com.lluengo.bci.exception.UserFoundedException;
import com.lluengo.bci.service.AuthService;
import com.lluengo.bci.utils.RegExValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lluengo.bci.jwt.JwtService;
import com.lluengo.bci.entity.User;
import com.lluengo.bci.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RegExValidator regExValidator;
    private final RegisterRequestToUserMapper registerRequestToUserMapper;

    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        user.setLastLogin(new Date());
        user.setToken(token);
        userRepository.save(user);
        return AuthResponseDto.builder()
            .token(token)
            .build();

    }

    public AuthResponseDto register(RegisterRequestDto request) {

        if (!regExValidator.validateEmail(request.getEmail())) throw new EmailValidationException(request.getEmail());

        if (!regExValidator.validatePassword(request.getPassword())) throw new PasswordException();

        userRepository.findByUsername(request.getEmail().trim()).ifPresent(s -> {
            throw new UserFoundedException(s);
        });

        User user = registerRequestToUserMapper.toUser(request);

        String token = jwtService.getToken(user);

        user.setToken(token);

        userRepository.save(user);

        return AuthResponseDto.builder()
            .created(user.getCreated())
            .id(user.getId())
            .lastLogin(user.getLastLogin())
            .token(jwtService.getToken(user))
            .isActive(Boolean.TRUE)
            .build();
        
    }

}
