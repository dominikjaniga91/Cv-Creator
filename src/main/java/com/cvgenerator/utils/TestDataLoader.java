package com.cvgenerator.utils;

import com.cvgenerator.domain.entity.*;
import com.cvgenerator.domain.enums.LanguageLevel;
import com.cvgenerator.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
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
    private PasswordEncoder passwordEncoder;
    private SummaryRepository summaryRepository;
    private ClauseRepository clauseRepository;

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
                          AddressRepository addressRepository,
                          PasswordEncoder passwordEncoder,
                          SummaryRepository summaryRepository,
                          ClauseRepository clauseRepository) {

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
        this.passwordEncoder = passwordEncoder;
        this.summaryRepository = summaryRepository;
        this.clauseRepository = clauseRepository;
    }

    @PostConstruct
    public void saveUsersToDatabase(){

        User user1 = createUser("Dominik", "Janiga", "dominikjaniga91@gmail.com",passwordEncoder.encode("dominik123"), true, "+48881463106", "ROLE_USER", LocalDateTime.now());
        User user2 = createUser("Andrzej", "Kowalski", "andrezjkowalski@gmail.com", passwordEncoder.encode("andrej123"), true, "+48881463106", "ROLE_USER", LocalDateTime.now());
        User user3 = createUser("Jurek", "Nowak", "jureknowak@gmail.com", passwordEncoder.encode("jurek123"), true, "+48881463106", "ROLE_USER", LocalDateTime.now());
        User user4 = createUser("Katarzyna", "Piekarska", "katarzynapiekarska@gmail.com", passwordEncoder.encode("kasia123"), true, "+48881463106", "ROLE_USER", LocalDateTime.now());
        User user5 = createUser("Anna", "Pompka", "annapompka@gmail.com", passwordEncoder.encode("anna123"), false, "+48881463106", "ROLE_USER", LocalDateTime.now());

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

        Summary summary1 = createSummary("summary 1");
        Summary summary2 = createSummary("summary 2");
        Summary summary3 = createSummary("summary 3");
        Summary summary4 = createSummary("summary 4");
        Summary summary5 = createSummary("summary 5");

        Clause clause1 = createClause("clause 1");
        Clause clause2 = createClause("clause 2");
        Clause clause3 = createClause("clause 3");
        Clause clause4 = createClause("clause 4");
        Clause clause5 = createClause("clause 5");



        UserCv cv1 = createUserCv("cv1", "aquarius",  user1,  clause1, summary1, personalData1);
        UserCv cv2 = createUserCv("cv2", "new york", user2, clause2, summary2, personalData2);
        UserCv cv3 = createUserCv("cv3", "basic", user3, clause3, summary3, personalData3);
        UserCv cv4 = createUserCv("cv4", "premium", user4, clause4, summary4, personalData4);
        UserCv cv5 = createUserCv("cv5", "semantic",user5, clause5, summary5, personalData5);



        createCourse("Altkom Akademia", "Kraków", LocalDate.of(2020,1,1), LocalDate.of(2020,6,30), "Java programming", cv1);
        createCourse("Kodilla", "Online", LocalDate.of(2020,1,1), LocalDate.of(2020,6,30), "JavaScript programming", cv2);
        createCourse("SDA", "Warszawa", LocalDate.of(2020,1,1), LocalDate.of(2020,6,30), "Python programming", cv3);
        createCourse("Altkom Akademia", "Poznań", LocalDate.of(2020,1,1), LocalDate.of(2020,6,30), "Front-end", cv4);
        createCourse("Coders Lab", "Kraków", LocalDate.of(2020,1,1), LocalDate.of(2020,6,30), "Node.JS programming", cv5);

        createEducation("AGH", "Kraków", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Master Degree", "Elektrotechnika", null, cv1);
        createEducation("Polotechnika Wrocławska", "Wrocaław", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Master Degree", "Automatyka", null, cv2);
        createEducation("AGH", "Kraków", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Master Degree", "Geologia", null, cv3);
        createEducation("Politechnika Warszawska", "Warszawa", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Master Degree", "Elektrotechnika", null, cv4);
        createEducation("AGH", "Kraków", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Master Degree", "Geofizyka", null, cv5);


        createExperience("Jeronimo Martins", "Kraków", "Magazynier", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), null, cv1);
        createExperience("Lpp", "Kraków", "Kontroller", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), null, cv2);
        createExperience("Uber", "Kraków", "Kierowca", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), null, cv3);
        createExperience("McDonaclds", "Kraków", "Sprzedawca", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), null, cv4);
        createExperience("Jeronimo Martins", "Kraków", "Sprzedawca", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), null, cv5);

        createInterest("Wspinaczka, Piwo, Koszykówka ", cv1);
        createInterest("Piłka nożna, Piwo, Koszykówka ", cv2);
        createInterest("Kolarstwo, Piwo, Netflix ", cv3);
        createInterest("Komputery, Piwo, Czytanie książek ", cv4);
        createInterest("Siatkówka, Piwo, Koszykówka ", cv5);

        createLanguage("English", null, LanguageLevel.B2, 3, cv1);
        createLanguage("Germany", null, LanguageLevel.B1, 3, cv2);
        createLanguage("French", null, LanguageLevel.C1, 4, cv3);
        createLanguage("English", null, LanguageLevel.C2, 5, cv4);
        createLanguage("Germany", null, LanguageLevel.B2, 3, cv5);

        createProject("Car Database", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Simple CRUD app", cv1);
        createProject("Flights Database", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Simple CRUD app", cv2);
        createProject("Konkurencja CROPP", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Simple CRUD app", cv3);
        createProject("Battleship game ", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "GRa w statki", cv4);
        createProject("Car Database", LocalDate.of(2015,1,1), LocalDate.of(2020,6,30), "Simple CRUD app", cv5);


        createSkills("Samoorganizacja", null, "Zaawansowany", 4, cv1);
        createSkills("Java", null, "Expert", 5, cv2);
        createSkills("Spring", null, "Zaawansowany", 4, cv3);
        createSkills("SQL", null, "Zaawansowany", 4, cv4);
        createSkills("GIT", null, "Zaawansowany", 4, cv5);

    }

    private Summary createSummary(String value) {
        Summary summary = new Summary();
        summary.setValue(value);
        summaryRepository.save(summary);
        return summary;
    }

    private Clause createClause(String value) {
        Clause clause = new Clause();
        clause.setValue(value);
        clauseRepository.save(clause);
        return clause;
    }

    private void createCourse(String school,
                              String city,
                              LocalDate startDate,
                              LocalDate finishDate,
                              String despription,
                              UserCv cv) {
        Course course = new Course();
        course.setSchool(school);
        course.setCity(city);
        course.setStartDate(startDate);
        course.setFinishDate(finishDate);
        course.setDescription(despription);
        course.setUserCv(cv);
        courseRepository.save(course);
    }

    private void createEducation(String school,
                                String city,
                                LocalDate startDate,
                                LocalDate finishDate,
                                String degree,
                                String specialization,
                                String description,
                                UserCv cv){

        Education education = new Education();
        education.setSchool(school);
        education.setCity(city);
        education.setStartDate(startDate);
        education.setFinishDate(finishDate);
        education.setDegree(degree);
        education.setSpecialization(specialization);
        education.setDescription(description);
        education.setUserCv(cv);
        educationRepository.save(education);
    }


    private void createExperience(String company,
                                 String city,
                                 String position,
                                 LocalDate startDate,
                                 LocalDate finishDate,
                                 String description,
                                 UserCv cv){

        Experience experience = new Experience();
        experience.setCompany(company);
        experience.setCity(city);
        experience.setPosition(position);
        experience.setStartDate(startDate);
        experience.setFinishDate(finishDate);
        experience.setDescription(description);
        experience.setUserCv(cv);
        experienceRepository.save(experience);
    }

    private void createInterest(String name, UserCv cv){

        Interest interest = new Interest();
        interest.setName(name);
        interest.setUserCv(cv);
        interestRepository.save(interest);

    }

    private void createLanguage(String name,
                                String description,
                                LanguageLevel level,
                                Integer stars,
                                UserCv cv){
        Language language = new Language();
        language.setName(name);
        language.setDescription(description);
        language.setLevel(level);
        language.setStars(stars);
        language.setUserCv(cv);
        languageRepository.save(language);

    }

    private void createProject(String name,
                                LocalDate startDate,
                                LocalDate finishDate,
                                String description,
                                UserCv cv){

        Project project = new Project();
        project.setName(name);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        project.setDescription(description);
        project.setUserCv(cv);
        projectRepository.save(project);
    }

    private void createSkills(String name,
                            String description,
                            String level,
                            Integer stars,
                              UserCv cv){

        Skill skill = new Skill();
        skill.setName(name);
        skill.setDescription(description);
        skill.setLevel(level);
        skill.setStars(stars);
        skill.setUserCv(cv);
        skillRepository.save(skill);
    }


    private User createUser(String firstName,
                             String lastName,
                             String email,
                             String password,
                             boolean isActive,
                             String phoneNumber,
                             String role,
                             LocalDateTime registration){

        User user  = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(isActive);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setRegistration(registration);
        userRepository.save(user);
        return user;
    }

    private UserCv createUserCv(String name,
                                String templateName,
                                User user,
                                Clause clause,
                                Summary summary,
                                PersonalData personalData){
        UserCv userCv  = new UserCv();
        userCv.setName(name);
        userCv.setTemplateName(templateName);
        userCv.setUser(user);
        userCv.setClause(clause);
        userCv.setSummary(summary);
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
