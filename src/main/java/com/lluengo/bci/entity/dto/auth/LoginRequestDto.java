package com.lluengo.bci.entity.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    String username;
    @NotBlank(message = "la contraseña es obligatorio")
    String password; 
}
