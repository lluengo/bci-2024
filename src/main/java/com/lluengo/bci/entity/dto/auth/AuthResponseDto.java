package com.lluengo.bci.entity.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    UUID id;
    Date created;
    Date modified;
    Date lastLogin;
    String token;
    Boolean isActive;
}
