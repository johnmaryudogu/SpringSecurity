package com.johnmary.SprongSecurityDemo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {

    private String responseCode;

    private String responseMessage;

    private RegisterationInfo registerationInfo;
}
