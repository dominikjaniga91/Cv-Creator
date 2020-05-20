package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Language;

public interface LanguageService {

    void saveLanguage(Long userCvId, Language language);
}
