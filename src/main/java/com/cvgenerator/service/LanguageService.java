package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Language;

public interface LanguageService {

    void createLanguage(Long userCvId, Language language);

    void updateLanguage(Language language);
}
