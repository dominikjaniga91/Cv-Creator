package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Skill;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.SkillRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {


    private final SkillRepository skillRepository;
    private final UserCvRepository userCvRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, UserCvRepository userCvRepository) {
        this.skillRepository = skillRepository;
        this.userCvRepository = userCvRepository;
    }

    @Override
    public void saveSkill(Long userCvId, Skill skill) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        skill.setUserCv(userCv);
        skillRepository.save(skill);
    }
}
