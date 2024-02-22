package com.lluengo.bci.entity.dto.auth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotBlank
    String email;
    @NotBlank
    String password;
    String name;
    String country;
    List<PhoneDto> phones;
}
