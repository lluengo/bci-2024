package com.lluengo.bci.service;

import com.lluengo.bci.entity.dto.auth.AuthResponseDto;
import com.lluengo.bci.entity.dto.auth.LoginRequestDto;
import com.lluengo.bci.entity.dto.auth.LoginResponseDto;
import com.lluengo.bci.entity.dto.auth.RegisterRequestDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);
    AuthResponseDto register(RegisterRequestDto request);
    }
