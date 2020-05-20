package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Address;
import com.cvgenerator.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.cvgenerator.repository.AddressRepository;
import com.cvgenerator.cvgenerator.repository.PersonalDataRepository;
import com.cvgenerator.cvgenerator.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private PersonalDataRepository dataRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, PersonalDataRepository dataRepository) {
        this.addressRepository = addressRepository;
        this.dataRepository = dataRepository;
    }

    @Override
    public void saveAddress(Long personalDataId, Address address) {
        PersonalData personalData = dataRepository.findById(personalDataId).orElseThrow();
        personalData.setAddress(address);
        addressRepository.save(address);
    }
}
