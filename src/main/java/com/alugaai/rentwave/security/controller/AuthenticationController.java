package com.alugaai.rentwave.security.controller;

import com.alugaai.rentwave.security.dto.AuthenticateInputDto;
import com.alugaai.rentwave.security.dto.AuthenticateOutputDto;
import com.alugaai.rentwave.security.dto.RegisterInputDto;
import com.alugaai.rentwave.security.dto.RegisterOutputDto;
import com.alugaai.rentwave.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterOutputDto> register(
            @RequestBody RegisterInputDto registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateOutputDto> authenticate(
            @RequestBody AuthenticateInputDto authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
