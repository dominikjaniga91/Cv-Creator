package com.cvgenerator.utils;

import com.cvgenerator.domain.entity.*;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.entity.UserCv;
import com.cvgenerator.repository.InterestRepository;
import com.cvgenerator.repository.LanguageRepository;
import com.cvgenerator.repository.*;
import com.cvgenerator.domain.entity.Address;
import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class TestDataLoader {

    private UserRepository userRepository;
    private UserCvRepository userCvRepository;
    private SkillRepository skillRepository;
    private ProjectRepository projectRepository;
    private PersonalDataRepository personalDataRepository;
    private LanguageRepository languageRepository;
    private InterestRepository interestRepository;
    private ExperienceRepository experienceRepository;
    private EducationRepository educationRepository;
    private CourseRepository courseRepository;
    private AddressRepository addressRepository;

    @Autowired
    public TestDataLoader(UserRepository userRepository,
                          UserCvRepository userCvRepository,
                          SkillRepository skillRepository,
                          ProjectRepository projectRepository,
                          PersonalDataRepository personalDataRepository,
                          LanguageRepository languageRepository,
                          InterestRepository interestRepository,
                          ExperienceRepository experienceRepository,
                          EducationRepository educationRepository,
                          CourseRepository courseRepository,
                          AddressRepository addressRepository) {

        this.userRepository = userRepository;
        this.userCvRepository = userCvRepository;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
        this.personalDataRepository = personalDataRepository;
        this.languageRepository = languageRepository;
        this.interestRepository = interestRepository;
        this.experienceRepository = experienceRepository;
        this.educationRepository = educationRepository;
        this.courseRepository = courseRepository;
        this.addressRepository = addressRepository;
    }

    @PostConstruct
    public void saveUsersToDatabase(){

        User user1 = createUser("Dominik", "Janiga", "dominikjaniga@gmail.com", "dominik123", true, LocalDateTime.now());
        User user2 = createUser("Andrzej", "Kowalski", "andrezjkowalski@gmail.com", "andrej123", true, LocalDateTime.now());
        User user3 = createUser("Jurek", "Nowak", "jureknowak@gmail.com", "jurek123", true, LocalDateTime.now());
        User user4 = createUser("Katarzyna", "Piekarska", "katarzynapiekarska@gmail.com", "kasia123", true, LocalDateTime.now());
        User user5 = createUser("Anna", "Pompka", "annapompka@gmail.com", "anna123", false, LocalDateTime.now());

        Address address1 = createAddress("Źródlana", 56, "33-111", "Koszyce Male");
        Address address2 = createAddress("Bitwy pod Lenino", 24, "80-809", "Gdansk");
        Address address3 = createAddress("Kochanowskiego", 25, "31-127", "Krakow");
        Address address4 = createAddress("Koszyceka", 6, "33-100", "Tarnow");
        Address address5 = createAddress("Aleje Jerozolimskie", 144, "00-101", "Warszawa");

        PersonalData personalData1 = createPersonalData("Dominik", "Rafał", "Janiga", "dominikjaniga@gmail.com", "881463195", "Java Developer", null, "https://github.com/dominikjaniga91", null, null, address1);
        PersonalData personalData2 = createPersonalData("Andrzej", "Kamil", "Kowalski", "andrezjkowalski@gmail.com", "465738482", "Mechanik", null, null, null, null, address2 );
        PersonalData personalData3 = createPersonalData("Jurek", "Bartosz", "Nowak", "jureknowak@gmail.com", "556748112", "Hydraulik", null, null, null, null, address3);
        PersonalData personalData4 = createPersonalData("Katarzyna", "Halina", "Piekarska", "katarzynapiekarska@gmail.com", "778465334", "Nauczycielka", null, null, null, null, address4);
        PersonalData personalData5 = createPersonalData("Anna", "Małgorzata", "Pompka", "annapompka@gmail.com", "998657332", "Dyrektor ds Administracji", null, null, null, null, address5);

        UserCv cv1 = createUserCv("cv1", "aquarius", " summary template1", " RODO clause1", user1, personalData1);
        UserCv cv2 = createUserCv("cv2", "new york", " summary template2", " RODO clause2", user2, personalData2);
        UserCv cv3 = createUserCv("cv3", "basic", " summary template3", " RODO clause3", user3, personalData3);
        UserCv cv4 = createUserCv("cv4", "premium", " summary template4", " RODO clause4", user4, personalData4);
        UserCv cv5 = createUserCv("cv5", "semantic", " summary template5", " RODO clause5", user5, personalData5);

    }

    private User createUser(String firstName,
                             String lastName,
                             String email,
                             String password,
                             boolean isActive,
                             LocalDateTime registration){
        User user  = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(isActive);
        user.setRegistration(registration);
        userRepository.save(user);
        return user;
    }

    private UserCv createUserCv(String name,
                                String templateName,
                                String summary,
                                String clause,
                                User user,
                                PersonalData personalData){
        UserCv userCv  = new UserCv();
        userCv.setName(name);
        userCv.setTemplateName(templateName);
        userCv.setSummary(summary);
        userCv.setClause(clause);
        userCv.setUser(user);
        userCv.setPersonalData(personalData);
        userCvRepository.save(userCv);
        return userCv;
    }

    private PersonalData createPersonalData(String firstName,
                                            String secondName,
                                            String lastName,
                                            String email,
                                            String phoneNumber,
                                            String profession,
                                            String linkedIn,
                                            String github,
                                            String twitter,
                                            String website,
                                            Address address){
        PersonalData personalData = new PersonalData();

        personalData.setFirstName(firstName);
        personalData.setSecondName(secondName);
        personalData.setLastName(lastName);
        personalData.setEmail(email);
        personalData.setPhoneNumber(phoneNumber);
        personalData.setProfession(profession);
        personalData.setLinkedIn(linkedIn);
        personalData.setGithub(github);
        personalData.setTwitter(twitter);
        personalData.setWebsite(website);
        personalData.setAddress(address);
        personalDataRepository.save(personalData);
        return personalData;
    }


    private Address createAddress(String street,
                                int houseNumber,
                                String zipCode,
                                String city){
        Address address = new Address();
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setZipCode(zipCode);
        address.setCity(city);

        return address;
    }


}
