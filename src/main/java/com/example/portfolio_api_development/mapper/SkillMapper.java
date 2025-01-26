package com.example.portfolio_api_development.mapper;

import com.example.portfolio_api_development.dao.entity.Skill;
import com.example.portfolio_api_development.model.dto.request.SkillRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateSkillFromRequest(SkillRequest skillRequest,@MappingTarget Skill skill);

    Skill toSkill(SkillRequest skillRequest);
}
