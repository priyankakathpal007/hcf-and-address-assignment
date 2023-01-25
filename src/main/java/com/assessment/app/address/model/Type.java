package com.assessment.app.address.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Type {
    @Expose
    private String code;
    @Expose
    private String name;

    public Type(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
