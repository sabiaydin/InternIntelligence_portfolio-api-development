package com.example.portfolio_api_development.service.impl;

import com.example.portfolio_api_development.config.JwtService;
import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.dao.repository.UserRepository;
import com.example.portfolio_api_development.mapper.UserMapper;
import com.example.portfolio_api_development.model.dto.request.AuthenticationRequest;
import com.example.portfolio_api_development.model.dto.request.RegisterRequest;
import com.example.portfolio_api_development.model.dto.response.AuthenticationResponse;
import com.example.portfolio_api_development.model.exception.AlreadyExistsException;
import com.example.portfolio_api_development.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("User with email " + request.getEmail() + " already exists.");
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
     var user = userRepository.findByEmail(request.getEmail())
             .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
