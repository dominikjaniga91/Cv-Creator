package com.cvgenerator.cvgenerator.service.implementation;

import com.cvgenerator.cvgenerator.domain.entity.Address;
import com.cvgenerator.cvgenerator.repository.AddressRepository;
import com.cvgenerator.cvgenerator.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
}
