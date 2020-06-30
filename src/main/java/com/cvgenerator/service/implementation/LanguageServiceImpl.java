package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Language;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.exceptions.notfound.LanguageNotFoundException;
import com.cvgenerator.repository.LanguageRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final UserCvRepository userCvRepository;
    private final Messages messages;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository,
                               UserCvRepository userCvRepository,
                               Messages messages) {
        this.languageRepository = languageRepository;
        this.userCvRepository = userCvRepository;
        this.messages = messages;
    }

    @Override
    public void createLanguage(Long userCvId, Language language) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        language.setUserCv(userCv);
        languageRepository.save(language);
    }

    @Override
    public void updateLanguage(Language language) {
        Long id = language.getId();
        Language foundedLanguage = languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException(messages.get("language.notfound")));

        foundedLanguage.setName(language.getName());
        foundedLanguage.setDescription(language.getDescription());
        foundedLanguage.setLevel(language.getLevel());
        foundedLanguage.setStars(language.getStars());

        languageRepository.save(foundedLanguage);
    }

    @Override
    public void deleteLanguageById(Long id) {
        try{
            languageRepository.deleteById(id);
        }catch (Exception ex){
            throw new LanguageNotFoundException(messages.get("language.notfound"));
        }
    }
}
