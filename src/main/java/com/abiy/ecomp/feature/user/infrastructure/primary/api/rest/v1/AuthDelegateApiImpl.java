package com.abiy.ecomp.feature.user.infrastructure.primary.api.rest.v1;

import com.abiy.ecomp.feature.user.application.UserApplicationService;
import com.abiy.ecomp.feature.user.domain.model.ChangePassword;
import com.abiy.ecomp.feature.user.infrastructure.primary.api.rest.v1.model.ResetPasswordRequest;
import com.abiy.ecomp.feature.user.infrastructure.primary.api.rest.v1.model.Token;
import com.abiy.ecomp.feature.user.infrastructure.primary.api.rest.v1.model.UserDetail;
import com.abiy.ecomp.feature.user.infrastructure.primary.mapper.ChangePasswordModelMapper;
import com.abiy.ecomp.feature.user.infrastructure.primary.mapper.TokenModelMapper;
import com.abiy.ecomp.feature.user.infrastructure.primary.mapper.UserDetailModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthDelegateApiImpl implements AuthsApiDelegate {

    private final ChangePasswordModelMapper changePasswordModelMapper;
    private final UserApplicationService userApplicationService;
    private final UserDetailModelMapper userDetailModelMapper;
    private final TokenModelMapper tokenModelMapper;

    @Override
    public ResponseEntity<Void> resetPassword(ResetPasswordRequest changePassword) {
        userApplicationService.changePassword(changePasswordModelMapper.toBo(changePassword));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> sendVerify(String userName, String password) {
        userApplicationService.sendVerify(userName, password);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> sendVerifySms(String phoneNumber, String password) {
        return AuthsApiDelegate.super.sendVerifySms(phoneNumber, password);
    }

    @Override
    public ResponseEntity<Void> updatePassword(String userName, String password, String newPassword, String confirmPassword) {
        userApplicationService.updatePassword(userName, new ChangePassword(password, newPassword, confirmPassword));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<UserDetail> currentUser() {
        return ResponseEntity.ok(userDetailModelMapper.toDto(userApplicationService.getUserDetail()));
    }

    @Override
    public ResponseEntity<Void> forgotPassword(String email) {
        userApplicationService.forgotPassword(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Token> login(String username, String password) {
        return ResponseEntity.ok(tokenModelMapper.toDto(userApplicationService.login(username, password)));
    }
}
