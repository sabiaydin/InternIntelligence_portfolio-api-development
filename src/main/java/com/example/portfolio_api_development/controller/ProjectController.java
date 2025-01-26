package com.example.portfolio_api_development.controller;

import com.example.portfolio_api_development.dao.entity.Project;
import com.example.portfolio_api_development.model.dto.request.ProjectRequest;
import com.example.portfolio_api_development.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectService.getAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> add(@Valid @RequestBody ProjectRequest projectRequest) {
        Project createdProject = projectService.add(projectRequest);
        return ResponseEntity.status(201).body(createdProject);
    }

    @PutMapping("/{id}")
    @PreAuthorize("!hasAuthority('ADMIN')")
    public ResponseEntity<Project> update(@PathVariable Long id,
                                          @Valid @RequestBody ProjectRequest projectRequest) {
        Project updatedProject = projectService.update(id, projectRequest);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
