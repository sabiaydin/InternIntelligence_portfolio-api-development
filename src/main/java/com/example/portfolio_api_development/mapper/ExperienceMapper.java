package com.example.portfolio_api_development.mapper;

import com.example.portfolio_api_development.dao.entity.Experience;
import com.example.portfolio_api_development.model.dto.request.ExperienceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    Experience toExperience(ExperienceRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateExperienceFromRequest(ExperienceRequest request, @MappingTarget Experience experience);
}
