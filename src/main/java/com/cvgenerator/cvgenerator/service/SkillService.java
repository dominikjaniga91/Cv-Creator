package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Skill;

public interface SkillService {

    void saveSkill(Long userCvId, Skill skill);
}
