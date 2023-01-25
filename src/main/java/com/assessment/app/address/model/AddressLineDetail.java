package com.assessment.app.address.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class AddressLineDetail {
    @Expose
    private String line1;
    @Expose
    private String line2;
}
