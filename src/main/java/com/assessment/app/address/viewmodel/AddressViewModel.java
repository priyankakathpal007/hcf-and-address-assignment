package com.assessment.app.address.viewmodel;

import com.assessment.app.address.model.Address;
import com.assessment.app.address.model.Type;
import com.assessment.app.address.repo.AddressRepository;
import com.assessment.app.address.utils.AddressValidator;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.assessment.app.address.Constants.*;

public class AddressViewModel {

    private final AddressRepository addressRepository;

    @Inject
    public AddressViewModel(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address fetchAddressById(@NonNull String id) {
        return addressRepository.getAddressById(id);
    }

    public Address fetchAddressByType(@NonNull String addressType) {
        switch (addressType) {
            case PHYSICAL_ADDRESS -> {
                return addressRepository.getAddressByType(new Type(PHYSICAL_ADDRESS_CODE, PHYSICAL_ADDRESS));
            }
            case POSTAL_ADDRESS -> {
                return addressRepository.getAddressByType(new Type(POSTAL_ADDRESS_CODE, POSTAL_ADDRESS));
            }
            case BUSINESS_ADDRESS -> {
                return addressRepository.getAddressByType(new Type(BUSINESS_ADDRESS_CODE, BUSINESS_ADDRESS));
            }
            default -> System.out.printf("Address type: %s is unknown.", addressType);
        }
        return null;
    }

    public List<Address> fetchAllAddresses() {
        return addressRepository.getAllAddresses();
    }

    public String prettyAddress(@NonNull Address address) {
        return String.format("%s: %s - %s - %s - %s - %s - %s",
            address.getType().getName(),
            address.getAddressLineDetail().getLine1(),
            address.getAddressLineDetail().getLine2(),
            address.getCityOrTown(),
            address.getProvinceOrState().getName(),
            address.getPostalCode(),
            address.getCountry().getName()
        );
    }

    public List<String> prettifiedAddresses() {
        List<String> prettifiedAddresses = new ArrayList<>();

        for (Address address: addressRepository.getAllAddresses()) {
            switch (address.getType().getCode()) {
                case PHYSICAL_ADDRESS_CODE -> prettifiedAddresses.add(physicalAddressPrettify(address));
                case POSTAL_ADDRESS_CODE -> prettifiedAddresses.add(postalAddressPrettify(address));
                case BUSINESS_ADDRESS_CODE -> prettifiedAddresses.add(businessAddressPrettify(address));
                default -> System.out.printf("Address type: %s is unknown.", address.getType().getCode());
            }
        }

        return prettifiedAddresses;
    }

    public String printAddressByType(@NonNull String addressType) {
        Address address = fetchAddressByType(addressType);

        switch (addressType) {
            case PHYSICAL_ADDRESS -> {
                return physicalAddressPrettify(address);
            }
            case POSTAL_ADDRESS -> {
                return postalAddressPrettify(address);
            }
            case BUSINESS_ADDRESS -> {
                return businessAddressPrettify(address);
            }
            default -> System.out.printf("Address type: %s is unknown.", addressType);
        }
        return null;
    }

    public AddressValidator.AddressValidation validateAddress(@NonNull Address address) {
        return AddressValidator.valid(address);
    }

    private String physicalAddressPrettify(@NonNull Address address) {
        return String.format(PHYSICAL_ADDRESS_FORMAT,
            address.getType().getName(),
            address.getAddressLineDetail().getLine1(),
            address.getAddressLineDetail().getLine2(),
            address.getCityOrTown(),
            address.getProvinceOrState().getName(),
            address.getPostalCode(),
            address.getCountry().getName()
        );
    }

    private String postalAddressPrettify(@NonNull Address address) {
        return String.format(POSTAL_ADDRESS_FORMAT,
            address.getType().getName(),
            address.getCityOrTown(),
            address.getPostalCode(),
            address.getCountry().getName()
        );
    }

    private String businessAddressPrettify(@NonNull Address address) {
        return String.format(BUSINESS_ADDRESS_FORMAT,
            address.getType().getName(),
            address.getAddressLineDetail().getLine1(),
            address.getCityOrTown(),
            address.getPostalCode(),
            address.getCountry().getName()
        );
    }

}
