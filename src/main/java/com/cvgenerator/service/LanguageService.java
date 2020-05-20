package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Language;

public interface LanguageService {

    void saveLanguage(Long userCvId, Language language);
}
