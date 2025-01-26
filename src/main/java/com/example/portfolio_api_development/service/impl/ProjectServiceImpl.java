package com.example.portfolio_api_development.service.impl;

import com.example.portfolio_api_development.dao.entity.Project;
import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.dao.repository.ProjectRepository;
import com.example.portfolio_api_development.dao.repository.UserRepository;
import com.example.portfolio_api_development.mapper.ProjectMapper;
import com.example.portfolio_api_development.model.dto.request.ProjectRequest;
import com.example.portfolio_api_development.model.exception.UserNotFoundException;
import com.example.portfolio_api_development.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;


    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found with id " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Project not found with id " + id);
        }
        projectRepository.deleteById(id);
    }

    @Override
    public Project update(Long id, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));
        projectMapper.updateProjectFromRequest(projectRequest, project);
        return projectRepository.save(project);
    }

    @Override
    public Project add(ProjectRequest projectRequest) {
        Project project = projectMapper.toProject(projectRequest);
        if (projectRequest.getUserId() != null) {
            User user = userRepository.findById(projectRequest.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id " + projectRequest.getUserId()));
            project.setUser(user);
        } else {
            throw new IllegalArgumentException("User ID is required");
        }
        return projectRepository.save(project);
    }
}
