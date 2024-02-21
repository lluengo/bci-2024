package com.lluengo.bci.service;

import com.lluengo.bci.entity.User;
import com.lluengo.bci.entity.dto.auth.AuthResponseDto;
import com.lluengo.bci.entity.dto.auth.LoginRequestDto;
import com.lluengo.bci.entity.dto.auth.RegisterRequestDto;
import com.lluengo.bci.exception.EmailValidationException;
import com.lluengo.bci.exception.PasswordException;
import com.lluengo.bci.jwt.JwtService;
import com.lluengo.bci.repository.UserRepository;
import com.lluengo.bci.service.impl.AuthServiceImpl;
import com.lluengo.bci.utils.RegExValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private RegExValidator regExValidator;

    @Test
    public void testSuccessfulLogin() {

        String username = "leonel.luengo@globallogic.com";
        String password = "Holamundo12";
        String token = "testtoken";

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername(username);
        loginRequestDto.setPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLastLogin(new Date());
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findByUsername(anyString())).thenReturn(optionalUser);
        when(jwtService.getToken(user)).thenReturn(token);
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));

        AuthResponseDto response = authService.login(loginRequestDto);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByUsername(username);
        verify(userRepository).save(user);
        verify(jwtService).getToken(user);

        assertEquals(token, response.getToken());
    }

    @Test
    public void testUnSuccessfulLoginUserName() {
        LoginRequestDto request = new LoginRequestDto("username", "password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        BadCredentialsException ex = assertThrows(BadCredentialsException.class, () -> {
            authService.login(request);
        });
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

    }

    @Test
    public void registerInvalidEmail() {

        RegisterRequestDto request = new RegisterRequestDto();
        request.setEmail("test..@gmail.com");
        request.setPassword("test");
        request.setName("test");
        request.setCountry("test");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(regExValidator.validateEmail(anyString())).thenReturn(false);

        EmailValidationException ex = assertThrows(EmailValidationException.class, () -> {
            authService.register(request);
        });
        verify(userRepository).findByUsername(request.getEmail());
        verify(regExValidator).validateEmail(request.getEmail());

    }

    @Test
    public void registerInvalidPassword() {

        RegisterRequestDto request = new RegisterRequestDto();
        request.setEmail("test.@gmail.com");
        request.setPassword("test");
        request.setName("test");
        request.setCountry("test");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(regExValidator.validateEmail(anyString())).thenReturn(true);
        when(regExValidator.validatePassword(anyString())).thenReturn(false);

        PasswordException ex = assertThrows(PasswordException.class, () -> {
            authService.register(request);
        });
        verify(userRepository).findByUsername(request.getEmail());
        verify(regExValidator).validateEmail(request.getEmail());
        verify(regExValidator).validatePassword(request.getPassword());

    }



}