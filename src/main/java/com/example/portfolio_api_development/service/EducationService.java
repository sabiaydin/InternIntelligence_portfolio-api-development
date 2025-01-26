package com.example.portfolio_api_development.service;

import com.example.portfolio_api_development.dao.entity.Education;
import com.example.portfolio_api_development.model.dto.request.EducationRequest;

import java.util.List;

public interface EducationService {
    List<Education> getAll();

    Education getById(Long id);

    Education add(EducationRequest educationRequest);

    Education update(Long id, EducationRequest educationRequest);

    void deleteById(Long id);
}
