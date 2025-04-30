package com.abiy.ecomp.feature.user.domain.repository;

import com.abiy.ecomp.feature.user.domain.model.ChangePassword;
import com.abiy.ecomp.feature.user.domain.model.CreateUser;
import com.abiy.ecomp.feature.user.domain.model.OAuth2TokenResult;
import com.abiy.ecomp.feature.user.domain.model.UserDetails;
import com.abiy.ecomp.shared.authentication.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<OAuth2TokenResult> login(String username, String password);

    void updatePassword(String username, ChangePassword changePassword);

    void forgotPassword(String email);

    void sendVerify(String username, String password);

    Optional<OAuth2TokenResult> refresh(String token);

    Optional<UserDetails> getUserDetailById(String userId);

    UserDetails createUser(CreateUser createUser, String role);

    void resetPassword(User user, ChangePassword changePassword);
}
