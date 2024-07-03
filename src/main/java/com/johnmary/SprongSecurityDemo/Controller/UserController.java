package com.johnmary.SprongSecurityDemo.Controller;

import com.johnmary.SprongSecurityDemo.Dto.AuthResponseDto;
import com.johnmary.SprongSecurityDemo.Dto.LoginRequestDto;
import com.johnmary.SprongSecurityDemo.Dto.LoginResponse;
import com.johnmary.SprongSecurityDemo.Dto.RegisterationDto;
import com.johnmary.SprongSecurityDemo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<AuthResponseDto> registerUser(
        @RequestBody RegisterationDto registerationDto){
        return ResponseEntity.ok(userService.registerUser(registerationDto));
    }

    @PostMapping("/login-user")
    public ResponseEntity<LoginResponse> loginUser (@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(userService.loginUser(loginRequestDto));

    }




    }

