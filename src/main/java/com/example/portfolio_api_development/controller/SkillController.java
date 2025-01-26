package com.example.portfolio_api_development.controller;

import com.example.portfolio_api_development.dao.entity.Skill;
import com.example.portfolio_api_development.model.dto.request.SkillRequest;
import com.example.portfolio_api_development.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService skillService;
    @GetMapping
    public ResponseEntity<List<Skill>> getAll() {
        List<Skill> skills = skillService.getAll();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable Long id) {
        Skill skill = skillService.getById(id);
        return ResponseEntity.ok(skill);
    }

    @PostMapping
    public ResponseEntity<Skill> add(@Valid @RequestBody SkillRequest skillRequest) {
        Skill createdSkill = skillService.add(skillRequest);
        return ResponseEntity.status(201).body(createdSkill);
    }

    @PutMapping("/{id}")
    @PreAuthorize("!hasAuthority('ADMIN')")
    public ResponseEntity<Skill> update(@PathVariable Long id,
                                        @Valid @RequestBody SkillRequest skillRequest) {
        Skill updatedSkill = skillService.update(id, skillRequest);
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
