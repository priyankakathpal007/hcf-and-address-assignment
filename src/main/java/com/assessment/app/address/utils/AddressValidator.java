package com.assessment.app.address.utils;

import com.assessment.app.address.model.Address;
import com.assessment.app.address.model.AddressLineDetail;
import com.assessment.app.address.model.Country;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AddressValidator {

    private static final String FIELD_POSTAL_CODE = "Postal Code";
    private static final String FIELD_COUNTRY = "Country";
    private static final String FIELD_ADDRESS_LINE_DETAILS = "Address Line Details";
    private static final String FIELD_PROVINCE = "Province";

    private static final String COUNTRY_CODE_ZA = "ZA";

    private AddressValidator() {
        /* no-ops */
    }

    public static AddressValidation valid(@NonNull Address address) {
        AddressValidation addressValidation = new AddressValidation();

        if (!isPostalCodeValid(address.getPostalCode())) {
            addressValidation.addInvalidField(FIELD_POSTAL_CODE);
            addressValidation.setValidation(false);
        }

        if (!isCountryValid(address.getCountry())) {
            addressValidation.addInvalidField(FIELD_COUNTRY);
            addressValidation.setValidation(false);
        }

        if (!isAddressLineDetailsValid(address.getAddressLineDetail())) {
            addressValidation.addInvalidField(FIELD_ADDRESS_LINE_DETAILS);
            addressValidation.setValidation(false);
        }

        if (!isProvinceValid(address)) {
            addressValidation.addInvalidField(FIELD_PROVINCE);
            addressValidation.setValidation(false);
        }

        return addressValidation;
    }

    public static class AddressValidation {
        private boolean validation = true;
        private final List<String> invalidFields = new ArrayList<>();

        public boolean isValid() {
            return validation;
        }

        public void setValidation(boolean validation) {
            this.validation = validation;
        }

        public List<String> getInvalidFields() {
            return invalidFields;
        }

        public void addInvalidField(String invalidField) {
            this.invalidFields.add(invalidField);
        }
    }

    public static boolean isPostalCodeValid(String postalCode) {
        try {
            Integer.parseInt(postalCode);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isAddressLineDetailsValid(AddressLineDetail addressLineDetail) {
        return addressLineDetail != null && (!addressLineDetail.getLine1().isEmpty() || !addressLineDetail.getLine2().isEmpty());
    }

    public static boolean isCountryValid(Country country) {
        return country != null;
    }

    public static boolean isProvinceValid(Address address) {
        return !address.getCountry().getCode().equals(COUNTRY_CODE_ZA) || (address.getProvinceOrState() != null
                && !address.getProvinceOrState().getName().isEmpty());
    }

}
