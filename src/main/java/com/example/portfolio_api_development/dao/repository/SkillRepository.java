package com.example.portfolio_api_development.dao.repository;

import com.example.portfolio_api_development.dao.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
}
