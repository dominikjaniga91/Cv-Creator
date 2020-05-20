package com.cvgenerator.cvgenerator.service;

import com.cvgenerator.cvgenerator.domain.entity.Address;

import java.util.Optional;

public interface AddressService {

   void saveAddress(Long personalDataId, Address address);
}
