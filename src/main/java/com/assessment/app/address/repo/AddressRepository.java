package com.assessment.app.address.repo;

import com.assessment.app.address.model.Address;
import com.assessment.app.address.model.Type;
import lombok.NonNull;

import java.util.List;

public interface AddressRepository {

    Address getAddressById(@NonNull String id);

    Address getAddressByType(@NonNull Type type);

    List<Address> getAllAddresses();

}
