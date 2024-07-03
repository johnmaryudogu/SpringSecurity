package com.johnmary.SprongSecurityDemo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterationInfo {

    private String firstName ;
    private String lastName ;
    private String email ;

}
