package com.example.portfolio_api_development.service.impl;

import com.example.portfolio_api_development.dao.entity.Experience;
import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.dao.repository.ExperienceRepository;
import com.example.portfolio_api_development.dao.repository.UserRepository;
import com.example.portfolio_api_development.mapper.ExperienceMapper;
import com.example.portfolio_api_development.model.dto.request.ExperienceRequest;
import com.example.portfolio_api_development.model.exception.UserNotFoundException;
import com.example.portfolio_api_development.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;
    private final UserRepository userRepository;

    @Override
    public List<Experience> getAll() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience getById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Experience not found with id " + id));
    }

    @Override
    public Experience add(ExperienceRequest experienceRequest) {
        Experience experience = experienceMapper.toExperience(experienceRequest);
        if (experienceRequest.getUserId() != null) {
            User user = userRepository.findById(experienceRequest.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id " + experienceRequest.getUserId()));
            experience.setUser(user);
        } else {
            throw new IllegalArgumentException("User ID is required");
        }
        return experienceRepository.save(experience);
    }

    @Override
    public Experience update(Long id, ExperienceRequest experienceRequest) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found with id " + id));
        experienceMapper.updateExperienceFromRequest(experienceRequest, experience);
        return experienceRepository.save(experience);
    }

    @Override
    public void deleteById(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new RuntimeException("Experience not found with id " + id);
        }
        experienceRepository.deleteById(id);
    }
}

