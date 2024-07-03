package com.johnmary.SprongSecurityDemo.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

    private  String responseaCode;

    private String responseMessage;

    private LoginInfo loginInfo;
}
