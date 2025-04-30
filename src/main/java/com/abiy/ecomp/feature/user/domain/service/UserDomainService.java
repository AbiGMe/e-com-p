package com.abiy.ecomp.feature.user.domain.service;

import com.abiy.ecomp.feature.user.domain.exception.UserErrorKey;
import com.abiy.ecomp.feature.user.domain.model.ChangePassword;
import com.abiy.ecomp.feature.user.domain.model.CreateUser;
import com.abiy.ecomp.feature.user.domain.model.OAuth2TokenResult;
import com.abiy.ecomp.feature.user.domain.model.UserDetails;
import com.abiy.ecomp.feature.user.domain.repository.UserRepository;
import com.abiy.ecomp.shared.authentication.application.AuthenticatedUser;
import com.abiy.ecomp.shared.authentication.domain.User;
import com.abiy.ecomp.shared.error.domain.Assert;
import com.abiy.ecomp.shared.error.domain.GeneratorException;

public class UserDomainService {

    private final UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OAuth2TokenResult login(String username, String password) {
        return userRepository.login(username, password)
            .orElseThrow(() -> GeneratorException.badRequest(UserErrorKey.USER_NOT_FOUND).message("User not found").build());
    }

    public OAuth2TokenResult refresh() {
        return userRepository.refresh(AuthenticatedUser.optionalToken().orElseThrow())
            .orElseThrow(() -> GeneratorException.badRequest(UserErrorKey.USER_NOT_FOUND).message("User not found").build());
    }

    public UserDetails getUserDetailById(String userId) {
        Assert.notBlank("User id", userId);

        return userRepository.getUserDetailById(userId)
            .orElseThrow(() -> GeneratorException.badRequest(UserErrorKey.USER_NOT_FOUND).message("User not found").build());
    }

    public UserDetails getUserDetail() {
        User user = AuthenticatedUser.getUser();

        return userRepository.getUserDetailById(user.id())
            .orElseThrow(() -> GeneratorException.badRequest(UserErrorKey.USER_NOT_FOUND).message("User not found").build());
    }

    public UserDetails createUser(CreateUser createUser, String role) {
        return userRepository.createUser(createUser, role);
    }

    public void resetPassword(ChangePassword changePassword) {
        var user = AuthenticatedUser.getUser();
        userRepository.resetPassword(user, changePassword);
    }

    public void forgotPassword(String email) {
        userRepository.forgotPassword(email);
    }

    public void updatePassword(String username, ChangePassword changePassword) {
        userRepository.updatePassword(username, changePassword);
    }

    public void sendVerify(String username, String password) {
        userRepository.sendVerify(username, password);
    }
}
