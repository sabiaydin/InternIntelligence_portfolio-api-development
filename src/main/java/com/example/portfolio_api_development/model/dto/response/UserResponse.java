package com.example.portfolio_api_development.model.dto.response;

import com.example.portfolio_api_development.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}
