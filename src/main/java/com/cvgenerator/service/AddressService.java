package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Address;

public interface AddressService {

   void saveAddress(Long personalDataId, Address address);
}
