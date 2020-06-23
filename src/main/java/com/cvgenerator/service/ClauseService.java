package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Clause;

public interface ClauseService {

    void createClause(Long userCvId, Clause clause);

    void updateClause(Clause clause);
}
