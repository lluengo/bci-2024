package com.lluengo.bci.entity.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
    private Long id;
    private long number;
    private int cityCode;
    private String countryCode;
}
