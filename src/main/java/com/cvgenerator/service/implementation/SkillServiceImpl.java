package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Skill;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.SkillNotFoundException;
import com.cvgenerator.exceptions.notfound.UserCvNotFoundException;
import com.cvgenerator.repository.SkillRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {


    private final SkillRepository skillRepository;
    private final UserCvRepository userCvRepository;
    private final Messages messages;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository,
                            UserCvRepository userCvRepository,
                            Messages messages) {
        this.skillRepository = skillRepository;
        this.userCvRepository = userCvRepository;
        this.messages = messages;
    }

    @Override
    public void saveSkill(Long userCvId, Skill skill) {

        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        skill.setUserCv(userCv);
        skillRepository.save(skill);
    }

    @Override
    public void updateSkill(Skill skill) {
        Long id = skill.getId();
        Skill foundedSkill = skillRepository.findById(id).orElseThrow(() -> new SkillNotFoundException(messages.get("skill.notfound")));

        foundedSkill.setName(skill.getName());
        foundedSkill.setDescription(skill.getDescription());
        foundedSkill.setLevel(skill.getLevel());
        foundedSkill.setStars(skill.getStars());

        skillRepository.save(foundedSkill);
    }

    @Override
    public void deleteSkillById(Long id) {
        try{
            skillRepository.deleteById(id);
        }catch (Exception ex){
            throw new SkillNotFoundException(messages.get("skill.notfound"));
        }

    }
}
