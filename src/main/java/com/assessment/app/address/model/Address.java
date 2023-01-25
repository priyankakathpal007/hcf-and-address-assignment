package com.assessment.app.address.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Address {
    @Expose
    private AddressLineDetail addressLineDetail;
    @Expose
    private String cityOrTown;
    @Expose
    private Country country;
    @Expose
    private String id;
    @Expose
    private String lastUpdated;
    @Expose
    private String postalCode;
    @Expose
    private ProvinceOrState provinceOrState;
    @Expose
    private Type type;
}
