package com.example.portfolio_api_development.service.impl;

import com.example.portfolio_api_development.dao.entity.Skill;
import com.example.portfolio_api_development.dao.entity.User;
import com.example.portfolio_api_development.dao.repository.SkillRepository;
import com.example.portfolio_api_development.dao.repository.UserRepository;
import com.example.portfolio_api_development.mapper.SkillMapper;
import com.example.portfolio_api_development.model.dto.request.SkillRequest;
import com.example.portfolio_api_development.model.exception.UserNotFoundException;
import com.example.portfolio_api_development.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final UserRepository userRepository;

    @Override
    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill not found with id " + id));
    }

    @Override
    public Skill add(SkillRequest skillRequest) {
        Skill skill = skillMapper.toSkill(skillRequest);
        if (skillRequest.getUserId() != null) {
            User user = userRepository.findById(skillRequest.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id " + skillRequest.getUserId()));
            skill.setUser(user);
        } else throw new IllegalArgumentException("User ID is required");
        return skillRepository.save(skill);
    }

    @Override
    public Skill update(Long id, SkillRequest skillRequest) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id " + id));
        skillMapper.updateSkillFromRequest(skillRequest, skill);
        return skillRepository.save(skill);
    }

    @Override
    public void deleteById(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new NotFoundException("Skill not found with id " + id);
        }
        skillRepository.deleteById(id);
    }
}
