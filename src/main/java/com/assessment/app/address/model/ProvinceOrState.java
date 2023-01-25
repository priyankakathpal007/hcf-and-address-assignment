package com.assessment.app.address.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ProvinceOrState {
    @Expose
    private String code;
    @Expose
    private String name;
}
