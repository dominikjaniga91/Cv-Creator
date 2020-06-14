package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.PersonalDataRepository;
import com.cvgenerator.repository.UserCvRepository;
import com.cvgenerator.service.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    private final UserCvRepository userCvRepository;
    private final PersonalDataRepository dataRepository;

    @Autowired
    public PersonalDataServiceImpl(UserCvRepository userCvRepository, PersonalDataRepository dataRepository) {
        this.userCvRepository = userCvRepository;
        this.dataRepository = dataRepository;
    }

    @Override
    public void createPersonalData(Long userCvId, PersonalData personalData) {
        UserCv userCv = userCvRepository.findById(userCvId).orElseThrow();
        personalData.setUserCv(userCv);
        dataRepository.save(personalData);
    }

    @Override
    public void updatePersonalData(PersonalData personalData) {
        Long id = personalData.getId();
        PersonalData foundedData = dataRepository.findById(id).orElseThrow();

        foundedData.setFirstName(personalData.getFirstName());
        foundedData.setSecondName(personalData.getSecondName());
        foundedData.setLastName(personalData.getLastName());
        foundedData.setEmail(personalData.getEmail());
        foundedData.setPhoneNumber(personalData.getPhoneNumber());
        foundedData.setProfession(personalData.getProfession());
        foundedData.setLinkedIn(personalData.getLinkedIn());
        foundedData.setGithub(personalData.getGithub());
        foundedData.setTwitter(personalData.getTwitter());
        foundedData.setWebsite(personalData.getWebsite());
        foundedData.setAddress(personalData.getAddress());

        dataRepository.save(foundedData);
    }

    @Override
    public void deletePersonalDataById(Long id) {
        PersonalData foundedData = dataRepository.findById(id).orElseThrow();
        UserCv cv = foundedData.getUserCv();
        cv.setPersonalData(null);
        dataRepository.deleteById(id);
    }
}
