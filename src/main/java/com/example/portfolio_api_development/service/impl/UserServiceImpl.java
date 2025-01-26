package com.example.portfolio_api_development.service.impl;

import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.dao.repository.UserRepository;
import com.example.portfolio_api_development.mapper.UserMapper;
import com.example.portfolio_api_development.model.dto.request.UserRequest;
import com.example.portfolio_api_development.model.dto.response.UserResponse;
import com.example.portfolio_api_development.model.exception.UserNotFoundException;
import com.example.portfolio_api_development.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper :: toUserResponse);
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
            userRequest.setPassword(encodedPassword);
        }

        userMapper.updateUserFromRequest(userRequest, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }


    @Override
    public void deleteById(Long id) {
    if(!userRepository.existsById(id)){
        throw new UserNotFoundException("User not found with id " + id);
    }
    userRepository.deleteById(id);
    }
}
