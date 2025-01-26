package com.example.portfolio_api_development.controller;

import com.example.portfolio_api_development.model.dto.request.AuthenticationRequest;
import com.example.portfolio_api_development.model.dto.request.RegisterRequest;
import com.example.portfolio_api_development.model.dto.response.AuthenticationResponse;
import com.example.portfolio_api_development.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ){
          service.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }
}
