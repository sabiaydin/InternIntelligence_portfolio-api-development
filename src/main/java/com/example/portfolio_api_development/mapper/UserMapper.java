package com.example.portfolio_api_development.mapper;

import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.model.dto.request.RegisterRequest;
import com.example.portfolio_api_development.model.dto.request.UserRequest;
import com.example.portfolio_api_development.model.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
    @Mapping(target = "id", ignore = true)
    void updateUserFromRequest(UserRequest userRequest,@MappingTarget User user);

    UserResponse toUserResponse(User user);
}
