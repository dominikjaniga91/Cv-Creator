package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Language;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.LanguageRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final UserCvRepository userCvRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository, UserCvRepository userCvRepository) {
        this.languageRepository = languageRepository;
        this.userCvRepository = userCvRepository;
    }

    @Override
    public void createLanguage(Long userCvId, Language language) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        language.setUserCv(userCv);
        languageRepository.save(language);
    }
}
