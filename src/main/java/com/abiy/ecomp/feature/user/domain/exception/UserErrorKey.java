package com.abiy.ecomp.feature.user.domain.exception;

import com.abiy.ecomp.shared.error.domain.ErrorKey;

public enum UserErrorKey implements ErrorKey {

    UPDATE_PASSWORD("update-password"),
    CONFIRM_PASSWORD_MISMATCH("confirm-password-mismatch"),
    VERIFY_EMAIL("verify-email"),
    USER_NOT_FOUND("user-not-found");

    private final String key;

    UserErrorKey(String key) {
        this.key = key;
    }

    @Override
    public String get() {
        return key;
    }
}
