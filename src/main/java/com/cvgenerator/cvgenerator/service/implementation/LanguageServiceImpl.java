package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Language;
import com.cvgenerator.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.cvgenerator.repository.LanguageRepository;
import com.cvgenerator.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.cvgenerator.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;
    private UserCvRepository userCvRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository, UserCvRepository userCvRepository) {
        this.languageRepository = languageRepository;
        this.userCvRepository = userCvRepository;
    }

    @Override
    public void saveLanguage(Long userCvId, Language language) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        language.setUserCv(userCv);
        languageRepository.save(language);
    }
}
