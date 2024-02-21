package com.lluengo.bci.service;

import com.lluengo.bci.entity.dto.auth.AuthResponseDto;
import com.lluengo.bci.entity.dto.auth.LoginRequestDto;
import com.lluengo.bci.entity.dto.auth.LoginResponseDto;
import com.lluengo.bci.entity.dto.auth.RegisterRequestDto;

public interface AuthService {
    public LoginResponseDto login(LoginRequestDto request);
    public AuthResponseDto register(RegisterRequestDto request);
    }
