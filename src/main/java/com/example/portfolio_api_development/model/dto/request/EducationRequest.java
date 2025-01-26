package com.example.portfolio_api_development.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequest {
    @NotBlank(message = "Institution name is required")
    private String institutionName;
    @NotBlank(message = "Degree is required")
    private String degree;
    private String startDate;
    private String endDate;
    private String description;
    private Long userId;
}
