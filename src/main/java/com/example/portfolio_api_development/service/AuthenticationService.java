package com.example.portfolio_api_development.service;

import com.example.portfolio_api_development.model.dto.request.AuthenticationRequest;
import com.example.portfolio_api_development.model.dto.request.RegisterRequest;
import com.example.portfolio_api_development.model.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    void register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
