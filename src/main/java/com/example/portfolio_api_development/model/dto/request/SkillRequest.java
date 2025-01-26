package com.example.portfolio_api_development.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillRequest {
    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Proficiency level is required")
    private String proficiencyLevel;
    private Long userId;
}
