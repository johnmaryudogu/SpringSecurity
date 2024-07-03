package com.johnmary.SprongSecurityDemo.Service;

import com.johnmary.SprongSecurityDemo.Dto.AuthResponseDto;
import com.johnmary.SprongSecurityDemo.Dto.LoginRequestDto;
import com.johnmary.SprongSecurityDemo.Dto.LoginResponse;
import com.johnmary.SprongSecurityDemo.Dto.RegisterationDto;

public interface UserService {

    AuthResponseDto registerUser(RegisterationDto registerationDto);

    LoginResponse loginUser(LoginRequestDto loginRequestDto);
}
