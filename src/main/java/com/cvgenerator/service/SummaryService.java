package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Summary;

public interface SummaryService {

    void createSummary(Long userCvId, Summary summary);

    void updateSummary(Summary summary);
}
