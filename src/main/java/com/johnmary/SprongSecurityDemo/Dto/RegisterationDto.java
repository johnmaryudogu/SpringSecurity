package com.johnmary.SprongSecurityDemo.Dto;

import com.johnmary.SprongSecurityDemo.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterationDto {

    private String firstName ;
    private String lastName ;
    private String email ;
    private String password ;

}
