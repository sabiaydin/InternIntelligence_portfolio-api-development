package com.example.portfolio_api_development.service;

import com.example.portfolio_api_development.dao.entity.Experience;
import com.example.portfolio_api_development.model.dto.request.ExperienceRequest;

import java.util.List;

public interface ExperienceService {
    List<Experience> getAll();

    Experience getById(Long id);

    Experience add(ExperienceRequest experienceRequest);

    Experience update(Long id, ExperienceRequest experienceRequest);

    void deleteById(Long id);
}
