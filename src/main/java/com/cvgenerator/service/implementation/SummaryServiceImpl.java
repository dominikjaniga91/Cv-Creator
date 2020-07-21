package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Summary;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.SummaryNotFoundException;
import com.cvgenerator.exceptions.notfound.UserCvNotFoundException;
import com.cvgenerator.repository.SummaryRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryServiceImpl implements SummaryService {

    private final UserCvRepository userCvRepository;
    private final SummaryRepository summaryRepository;
    private final Messages messages;

    @Autowired
    public SummaryServiceImpl(UserCvRepository userCvRepository,
                              SummaryRepository summaryRepository,
                              Messages messages) {
        this.userCvRepository = userCvRepository;
        this.summaryRepository = summaryRepository;
        this.messages = messages;
    }

    @Override
    public void createSummary(Long userCvId, Summary summary) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow(() -> new UserCvNotFoundException(messages.get("userCv.notfound")));
        userCv.setSummary(summary);
        userCvRepository.save(userCv);
    }

    @Override
    public void updateSummary(Summary summary) {
        Long id = summary.getId();
        Summary foundedSummary = summaryRepository.findById(id).orElseThrow(() -> new SummaryNotFoundException(messages.get("summary.notfound")));
        foundedSummary.setValue(summary.getValue());
        summaryRepository.save(summary);
    }

    @Override
    public void deleteSummaryById(Long id) {
        Summary foundedData = summaryRepository.findById(id).orElseThrow(() -> new SummaryNotFoundException(messages.get("summary.notfound")));
        UserCv cv = foundedData.getUserCv();
        cv.setSummary(null);
        summaryRepository.deleteById(id);
    }
}
