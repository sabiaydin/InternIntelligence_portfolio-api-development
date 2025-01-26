package com.example.portfolio_api_development.service;

import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.model.dto.request.UserRequest;
import com.example.portfolio_api_development.model.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> getAll(Pageable pageable);

    UserResponse getById(Long id);

    UserResponse update(Long id, UserRequest userRequest);

    void deleteById(Long id);

}
