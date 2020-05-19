package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Skill;

public interface SkillService {

    Skill saveSkill(Long userCvId, Skill skill);
}
