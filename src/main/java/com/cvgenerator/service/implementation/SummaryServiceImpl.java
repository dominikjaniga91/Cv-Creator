package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Address;
import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.domain.entity.Summary;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.PersonalDataNotFoundException;
import com.cvgenerator.exceptions.notfound.UserCvNotFoundException;
import com.cvgenerator.repository.AddressRepository;
import com.cvgenerator.repository.PersonalDataRepository;
import com.cvgenerator.repository.SummaryRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.PersonalDataService;
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
        summary.setUserCv(userCv);
        summaryRepository.save(summary);
    }

}
