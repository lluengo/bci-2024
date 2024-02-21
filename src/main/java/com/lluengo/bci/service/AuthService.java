package com.lluengo.bci.service;

import com.lluengo.bci.entity.dto.auth.AuthResponseDto;
import com.lluengo.bci.entity.dto.auth.LoginRequestDto;
import com.lluengo.bci.entity.dto.auth.RegisterRequestDto;

public interface AuthService {
    public AuthResponseDto login(LoginRequestDto request);
    public AuthResponseDto register(RegisterRequestDto request);
    }
