package com.assessment.app.address.view;

import com.assessment.app.address.di.AddressModule;
import com.assessment.app.address.model.Address;
import com.assessment.app.address.utils.AddressValidator;
import com.assessment.app.address.viewmodel.AddressViewModel;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static com.assessment.app.address.Constants.*;

public class Client {

    private static AddressViewModel addressViewModel;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AddressModule());
        addressViewModel = injector.getInstance(AddressViewModel.class);

        Address physicalAddress = addressViewModel.fetchAddressById("1");
        Address postalAddress = addressViewModel.fetchAddressById("2");
        Address businessAddress = addressViewModel.fetchAddressById("3");

        // a. Pretty version of an address
        prettyPrintAddress(physicalAddress);
        System.out.println("\n-----------------------------------------------\n");

        // b. Pretty print all the addresses in the attached file
        prettyPrintAllAddress();
        System.out.println("\n-----------------------------------------------\n");

        // c. Print an address of a certain type
        printAddressByType(PHYSICAL_ADDRESS);
        printAddressByType(POSTAL_ADDRESS);
        printAddressByType(BUSINESS_ADDRESS);
        System.out.println("\n-----------------------------------------------\n");

        // d. and e. Validate an address
        validateAddress(physicalAddress);
        validateAddress(postalAddress);
        validateAddress(businessAddress);
    }

    private static void prettyPrintAddress(Address address) {
        System.out.println(addressViewModel.prettyAddress(address));
    }

    private static void prettyPrintAllAddress() {
        for (String prettifiedAddress: addressViewModel.prettifiedAddresses()) {
            System.out.println(prettifiedAddress);
        }
    }

    private static void printAddressByType(String addressType) {;
        System.out.println(addressViewModel.printAddressByType(addressType));
    }

    private static void validateAddress(Address address) {
        AddressValidator.AddressValidation addressValidation = addressViewModel.validateAddress(address);
        String validationMessage = addressValidation.isValid() ? "a valid address."
                : "not a valid address. Missing field(s): " + addressValidation.getInvalidFields();
        System.out.printf("%s is %s \n", address.getType().getName(), validationMessage);
    }

}
