package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Clause;
import com.cvgenerator.domain.entity.Summary;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.SummaryNotFoundException;
import com.cvgenerator.exceptions.notfound.UserCvNotFoundException;
import com.cvgenerator.repository.ClauseRepository;
import com.cvgenerator.repository.SummaryRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.ClauseService;
import com.cvgenerator.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClauseServiceImpl implements ClauseService {

    private final UserCvRepository userCvRepository;
    private final ClauseRepository clauseRepository;
    private final Messages messages;

    @Autowired
    public ClauseServiceImpl(UserCvRepository userCvRepository,
                             ClauseRepository clauseRepository,
                             Messages messages) {
        this.userCvRepository = userCvRepository;
        this.clauseRepository = clauseRepository;
        this.messages = messages;
    }

    @Override
    public void createClause(Long userCvId, Clause clause) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        clause.setUserCv(userCv);
        clauseRepository.save(clause);
    }
}
