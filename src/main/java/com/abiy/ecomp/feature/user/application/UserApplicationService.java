package com.abiy.ecomp.feature.user.application;

import com.abiy.ecomp.feature.user.domain.model.ChangePassword;
import com.abiy.ecomp.feature.user.domain.model.CreateUser;
import com.abiy.ecomp.feature.user.domain.model.OAuth2TokenResult;
import com.abiy.ecomp.feature.user.domain.model.UserDetails;

public interface UserApplicationService {

    OAuth2TokenResult login(String username, String password);

    OAuth2TokenResult refresh();

    UserDetails getUserDetailById(String userId);

    void forgotPassword(String email);

    UserDetails getUserDetail();

    UserDetails createUser(CreateUser createUser, String role);

    void changePassword(ChangePassword changePassword);

    void updatePassword(String username, ChangePassword changePassword);

    void sendVerify(String username, String password);
}
