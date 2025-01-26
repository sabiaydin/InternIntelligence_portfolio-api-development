package com.example.portfolio_api_development.mapper;

import com.example.portfolio_api_development.dao.entity.Education;
import com.example.portfolio_api_development.model.dto.request.EducationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    Education toEducation(EducationRequest educationRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEducationFromRequest(EducationRequest educationRequest, @MappingTarget Education education);
}
