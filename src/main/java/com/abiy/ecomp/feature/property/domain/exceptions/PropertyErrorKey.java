package com.abiy.ecomp.feature.property.domain.exceptions;

import com.abiy.ecomp.shared.error.domain.ErrorKey;

public enum PropertyErrorKey implements ErrorKey {

    PROPERTY_NOT_FOUND("property.not.found"),
    PROPERTY_MISSING_REQUIRED_FIELDS("property.missing.required.fields");

    private final String key;

    PropertyErrorKey(String key) {
        this.key = key;
    }


    @Override
    public String get() {
        return this.key;
    }
}
