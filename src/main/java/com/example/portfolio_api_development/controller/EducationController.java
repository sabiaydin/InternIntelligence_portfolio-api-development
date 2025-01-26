package com.example.portfolio_api_development.controller;

import com.example.portfolio_api_development.dao.entity.Education;
import com.example.portfolio_api_development.model.dto.request.EducationRequest;
import com.example.portfolio_api_development.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/educations")
public class EducationController {
    private final EducationService educationService;

    @GetMapping
    public ResponseEntity<List<Education>> getAll() {
        List<Education> educations = educationService.getAll();
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getById(@PathVariable Long id) {
        Education education = educationService.getById(id);
        return ResponseEntity.ok(education);
    }

    @PostMapping()
    public ResponseEntity<Education> add(@Valid @RequestBody EducationRequest educationRequest) {
        Education createdEducation = educationService.add(educationRequest);
        return ResponseEntity.status(201).body(createdEducation);
    }

    @PutMapping("/{id}")
    @PreAuthorize("!hasAuthority('ADMIN')")
    public ResponseEntity<Education> update(@PathVariable Long id,
                                            @Valid @RequestBody EducationRequest educationRequest) {
        Education updatedEducation = educationService.update(id, educationRequest);
        return ResponseEntity.ok(updatedEducation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        educationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
