package com.example.portfolio_api_development.service;

import com.example.portfolio_api_development.dao.entity.Skill;
import com.example.portfolio_api_development.model.dto.request.SkillRequest;

import java.util.List;

public interface SkillService {
    List<Skill> getAll();

    Skill getById(Long id);

    Skill add(SkillRequest skillRequest);

    Skill update(Long id, SkillRequest skillRequest);

    void deleteById(Long id);
}
