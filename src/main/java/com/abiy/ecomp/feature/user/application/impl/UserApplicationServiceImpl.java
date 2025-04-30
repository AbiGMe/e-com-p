package com.abiy.ecomp.feature.user.application.impl;

import com.abiy.ecomp.feature.user.application.UserApplicationService;
import com.abiy.ecomp.feature.user.domain.model.ChangePassword;
import com.abiy.ecomp.feature.user.domain.model.CreateUser;
import com.abiy.ecomp.feature.user.domain.model.OAuth2TokenResult;
import com.abiy.ecomp.feature.user.domain.model.UserDetails;
import com.abiy.ecomp.feature.user.domain.repository.UserRepository;
import com.abiy.ecomp.feature.user.domain.service.UserDomainService;

public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;

    public UserApplicationServiceImpl(UserRepository userRepository) {
        this.userDomainService = new UserDomainService(userRepository);
    }

    @Override
    public OAuth2TokenResult login(String username, String password) {
        return userDomainService.login(username, password);
    }

    @Override
    public OAuth2TokenResult refresh() {
        return userDomainService.refresh();
    }

    @Override
    public UserDetails getUserDetailById(String userId) {
        return userDomainService.getUserDetailById(userId);
    }

    @Override
    public void forgotPassword(String email) {
        userDomainService.forgotPassword(email);
    }

    @Override
    public UserDetails getUserDetail() {
        return userDomainService.getUserDetail();
    }

    @Override
    public UserDetails createUser(CreateUser createUser, String role) {
        return userDomainService.createUser(createUser, role);
    }

    @Override
    public void changePassword(ChangePassword changePassword) {
        userDomainService.resetPassword(changePassword);
    }

    @Override
    public void updatePassword(String username, ChangePassword changePassword) {
        userDomainService.updatePassword(username, changePassword);
    }

    @Override
    public void sendVerify(String username, String password) {
        userDomainService.sendVerify(username, password);
    }
}
