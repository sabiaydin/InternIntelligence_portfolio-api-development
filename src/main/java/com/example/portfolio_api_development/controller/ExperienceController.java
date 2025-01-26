package com.example.portfolio_api_development.controller;

import com.example.portfolio_api_development.dao.entity.Experience;
import com.example.portfolio_api_development.model.dto.request.ExperienceRequest;
import com.example.portfolio_api_development.service.ExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/experience")
public class ExperienceController {
    private final ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<List<Experience>> getAll() {
        List<Experience> experiences = experienceService.getAll();
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getById(@PathVariable Long id) {
        Experience experience = experienceService.getById(id);
        return ResponseEntity.ok(experience);
    }

    @PostMapping
    public ResponseEntity<Experience> add(@Valid @RequestBody ExperienceRequest experienceRequest) {
        Experience createdExperience = experienceService.add(experienceRequest);
        return ResponseEntity.status(201).body(createdExperience);
    }

    @PutMapping("/{id}")
    @PreAuthorize("!hasAuthority('ADMIN')")
    public ResponseEntity<Experience> update(@PathVariable Long id,
                                             @Valid @RequestBody ExperienceRequest experienceRequest) {
        Experience updatedExperience = experienceService.update(id, experienceRequest);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        experienceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
