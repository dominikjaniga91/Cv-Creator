package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Skill;

public interface SkillService {

    void createSkill(Long userCvId, Skill skill);

    void updateSkill(Skill skill);

    void deleteSkillById(Long id);
}
