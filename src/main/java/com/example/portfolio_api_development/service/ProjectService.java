package com.example.portfolio_api_development.service;

import com.example.portfolio_api_development.dao.entity.Project;
import com.example.portfolio_api_development.model.dto.request.ProjectRequest;

import java.util.List;

public interface ProjectService {
    List<Project> getAll();

    Project getById(Long id);

    Project add(ProjectRequest projectRequest);

    Project update(Long id, ProjectRequest projectRequest);

    void deleteById(Long id);
}
