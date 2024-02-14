package com.lluengo.bci.entity.dto.auth;

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
    String email;
    String password;
    String name;
    String country;
    List<PhoneDto> phones;
}
