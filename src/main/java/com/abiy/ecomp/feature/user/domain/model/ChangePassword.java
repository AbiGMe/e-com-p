package com.abiy.ecomp.feature.user.domain.model;

import com.abiy.ecomp.feature.user.domain.exception.UserErrorKey;
import com.abiy.ecomp.shared.error.domain.Assert;
import com.abiy.ecomp.shared.error.domain.GeneratorException;
import org.apache.commons.lang3.StringUtils;

public record ChangePassword(String oldPassword, String newPassword, String confirmPassword) {

    public ChangePassword {
        Assert.field("Old Password", oldPassword)
            .minLength(8)
            .notBlank();
        Assert.field("New Password", newPassword)
            .minLength(8)
            .notBlank();
        Assert.field("Confirm password", confirmPassword)
            .minLength(8)
            .notBlank();

        if (!StringUtils.equals(newPassword, confirmPassword)) {
            throw GeneratorException.badRequest(UserErrorKey.CONFIRM_PASSWORD_MISMATCH).message("password and confirm password are not equal.").build();
        }
    }
}
