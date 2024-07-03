package com.johnmary.SprongSecurityDemo.Impl;

import com.johnmary.SprongSecurityDemo.Config.JwtService;
import com.johnmary.SprongSecurityDemo.Dto.*;
import com.johnmary.SprongSecurityDemo.Entity.UserEntity;
import com.johnmary.SprongSecurityDemo.Enums.Role;
import com.johnmary.SprongSecurityDemo.Repository.UserRepository;
import com.johnmary.SprongSecurityDemo.Service.EmailService;
import com.johnmary.SprongSecurityDemo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSevriceImpl implements UserService {

   private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    @Override
    public AuthResponseDto registerUser(RegisterationDto registrationDto) {
        //this gets the information of the user and save to database
        UserEntity user = UserEntity.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);


        //Email Alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("Account Creation")
                .messageBody("congratulations , Your account has been successfully created ")
                .build();

        emailService.sendEmailAlert(emailDetails);



        return AuthResponseDto.builder()
                //upon collecting the details from the user, we will show this details to the user as his login details
                .responseCode("001")
                .responseMessage("Account Created Successfully")
                .registerationInfo(RegisterationInfo.builder()
                        .firstName(registrationDto.getFirstName())
                        .lastName(registrationDto.getLastName())
                        .email(registrationDto.getEmail())
                        .build())
                .build();
    }

    @Override
    public LoginResponse loginUser(LoginRequestDto loginRequestDto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        ));

        UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder()
                .responseaCode("002")
                .responseMessage("Logon Successfully")
                .loginInfo(LoginInfo.builder()
                .email(user.getEmail())
                        .token(jwtToken)
                        .build())
                        .build();


    }
}
