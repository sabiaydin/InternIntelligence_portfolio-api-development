package com.example.portfolio_api_development.mapper;

import com.example.portfolio_api_development.dao.entity.Project;
import com.example.portfolio_api_development.model.dto.request.ProjectRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateProjectFromRequest(ProjectRequest request, @MappingTarget Project project);
}
