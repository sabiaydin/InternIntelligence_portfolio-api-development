package com.example.portfolio_api_development.service.impl;

import com.example.portfolio_api_development.dao.entity.Education;
import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.dao.repository.EducationRepository;
import com.example.portfolio_api_development.dao.repository.UserRepository;
import com.example.portfolio_api_development.mapper.EducationMapper;
import com.example.portfolio_api_development.model.dto.request.EducationRequest;
import com.example.portfolio_api_development.model.exception.UserNotFoundException;
import com.example.portfolio_api_development.service.EducationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;
    private final UserRepository userRepository;

    @Override
    public List<Education> getAll() {
        return educationRepository.findAll();
    }

    @Override
    public Education getById(Long id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Education not found with id " + id));
    }

    @Override
    public Education add(EducationRequest educationRequest) {
        Education education = educationMapper.toEducation(educationRequest);
        if (educationRequest.getUserId() != null) {
            User user = userRepository.findById(educationRequest.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id " + educationRequest.getUserId()));
            education.setUser(user);
        } else {
            throw new IllegalArgumentException("User ID is required");
        }
        return educationRepository.save(education);
    }

    @Override
    public Education update(Long id, EducationRequest educationRequest) {
        Education education = educationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Education not found with id " + id));
        educationMapper.updateEducationFromRequest(educationRequest, education);
        return educationRepository.save(education);
    }

    @Override
    public void deleteById(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new NotFoundException("Education not found with id " + id);
        }
        educationRepository.deleteById(id);
    }
}
