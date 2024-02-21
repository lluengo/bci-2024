package com.lluengo.bci.controller;

import com.lluengo.bci.entity.dto.auth.AuthResponseDto;
import com.lluengo.bci.service.impl.AuthServiceImpl;
import com.lluengo.bci.entity.dto.auth.LoginRequestDto;
import com.lluengo.bci.entity.dto.auth.RegisterRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthServiceImpl authService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request)
    {
        return new ResponseEntity<AuthResponseDto>(authService.register(request), HttpStatus.CREATED);
    }
}
