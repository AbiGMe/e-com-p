package com.abiy.ecomp.feature.user.infrastructure.secondary.repository;

import com.abiy.ecomp.feature.user.domain.exception.UserErrorKey;
import com.abiy.ecomp.feature.user.domain.model.*;
import com.abiy.ecomp.feature.user.domain.repository.UserRepository;
import com.abiy.ecomp.feature.user.infrastructure.secondary.config.KeycloakClientConfig;
import com.abiy.ecomp.feature.user.infrastructure.secondary.model.Credential;
import com.abiy.ecomp.feature.user.infrastructure.secondary.model.RoleRepresentation;
import com.abiy.ecomp.feature.user.infrastructure.secondary.model.UserRequestDto;
import com.abiy.ecomp.feature.user.infrastructure.secondary.openfeign.KeycloakAdminClient;
import com.abiy.ecomp.feature.user.infrastructure.secondary.openfeign.KeycloakAuthFeignClient;
import com.abiy.ecomp.shared.authentication.domain.User;
import com.abiy.ecomp.shared.error.domain.Assert;
import com.abiy.ecomp.shared.error.domain.GeneratorException;
import com.abiy.ecomp.shared.openfeign.infrastructure.secondary.OpenFeignErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryServiceImpl implements UserRepository {

    private final KeycloakAuthFeignClient keycloakAuthFeignClient;
    private final KeycloakClientConfig keycloakClientConfig;
    private final KeycloakAdminClient keycloakAdminClient;

    @Override
    public Optional<OAuth2TokenResult> login(String username, String password) {
        try {
            OAuth2TokenResult oAuth2TokenResult = keycloakAuthFeignClient.refresh(UserLogin.builder()
                .username(username)
                .password(password)
                .client_id(keycloakClientConfig.getClient().getId())
                .client_secret(keycloakClientConfig.getClient().getSecret())
                .grant_type("password")
                .build());
            return Optional.ofNullable(oAuth2TokenResult);
        } catch (OpenFeignErrorDecoder.OpenFeignException ex) {
            if (ex.getStatus() != 401) {
                final OAuth2TokenResult tokenResult = authenticateClient();
                final String bearerToken = "bearer " + tokenResult.getAccessToken();
                UserDetails userDetail = keycloakAdminClient.getUserByUsername(bearerToken, username, null)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> ex);
                if (userDetail.getRequiredActions().contains("UPDATE_PASSWORD")) {
                    throw GeneratorException.badRequest(UserErrorKey.UPDATE_PASSWORD).message("You need to change your password to activate your account.").build();
                } else if (userDetail.getRequiredActions().contains("VERIFY_EMAIL")) {
                    throw GeneratorException.badRequest(UserErrorKey.VERIFY_EMAIL).message("You need to verify your email address to activate your account.").build();
                }
            }
            throw ex;
        }
    }

    @Override
    public void updatePassword(String username, ChangePassword changePassword) {
        try {
            keycloakAuthFeignClient.refresh(UserLogin.builder()
                .username(username)
                .password(changePassword.oldPassword())
                .client_id(keycloakClientConfig.getClient().getId())
                .client_secret(keycloakClientConfig.getClient().getSecret())
                .grant_type("password")
                .build());
        } catch (OpenFeignErrorDecoder.OpenFeignException ex) {
            if (ex.getStatus() != 401) {
                final OAuth2TokenResult tokenResult = authenticateClient();
                final String bearerToken = "bearer " + tokenResult.getAccessToken();
                UserDetails userDetail = keycloakAdminClient.getUserByUsername(bearerToken, username, null)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> ex);
                if (userDetail.getRequiredActions().contains("UPDATE_PASSWORD")) {
                    keycloakAdminClient.resetPassword(bearerToken, userDetail.getId(), Credential.builder()
                        .type("password")
                        .value(changePassword.newPassword())
                        .temporary(false)
                        .build());
                    return;
                }
            }
            throw ex;
        }
    }

    @Override
    public void forgotPassword(String email) {
        final OAuth2TokenResult tokenResult = authenticateClient();
        final String bearerToken = "bearer " + tokenResult.getAccessToken();

        UserDetails userDetails = keycloakAdminClient.getUserByUsername(bearerToken, null, email)
            .stream().findFirst()
            .orElseThrow(() -> GeneratorException.badRequest(UserErrorKey.USER_NOT_FOUND).message("User not found").build());

        keycloakAdminClient.forgotPassword(bearerToken, userDetails.getId());
    }

    @Override
    public void sendVerify(String username, String password) {
        try {
            keycloakAuthFeignClient.refresh(UserLogin.builder()
                .username(username)
                .password(password)
                .client_id(keycloakClientConfig.getClient().getId())
                .client_secret(keycloakClientConfig.getClient().getSecret())
                .grant_type("password")
                .build());
        } catch (OpenFeignErrorDecoder.OpenFeignException ex) {
            if (ex.getStatus() != 401) {
                final OAuth2TokenResult tokenResult = authenticateClient();
                final String bearerToken = "bearer " + tokenResult.getAccessToken();
                UserDetails userDetail = keycloakAdminClient.getUserByUsername(bearerToken, username, null)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> ex);
                if (userDetail.getRequiredActions().contains("VERIFY_EMAIL")) {
                    keycloakAdminClient.sendVerifyEmail(bearerToken, userDetail.getId());
                    return;
                }
            }
            throw ex;
        }
    }

    @Override
    public Optional<OAuth2TokenResult> refresh(String refreshToken) {
        OAuth2TokenResult oAuth2TokenResult = keycloakAuthFeignClient.refresh(UserLogin.builder()
            .client_id(keycloakClientConfig.getClient().getId())
            .client_secret(keycloakClientConfig.getClient().getSecret())
            .refresh_token(refreshToken)
            .grant_type("refresh_token")
            .build());

        return Optional.ofNullable(oAuth2TokenResult);
    }

    @Override
    public Optional<UserDetails> getUserDetailById(String userId) {

        return Optional.of(keycloakAdminClient.getUser(formatAuthentication(), userId));
    }

    @Override
    public UserDetails createUser(CreateUser createUser, String role) {
        final UserRequestDto userRequestDto = UserRequestDto.builder()
            .username(createUser.username())
            .firstName(createUser.firstName())
            .lastName(createUser.lastName())
            .email(createUser.email())
            .realmRoles(List.of(role))
            .credentials(Collections.singletonList(Credential.builder().type("password").temporary(false).value(createUser.password()).build()))
            .attributes(Map.of("phoneNumber", List.of(createUser.phoneNumber())))
            .build();
        var token = formatAuthentication();
        keycloakAdminClient.registerUser(token, userRequestDto);

        UserDetails details = keycloakAdminClient.getUserByUsername(token, createUser.username(), null)
            .stream().findFirst()
            .orElseThrow(() -> GeneratorException.badRequest(UserErrorKey.USER_NOT_FOUND).message("User not found").build());

        List<RoleRepresentation> roles = keycloakAdminClient.roles(token, details.getId())
            .stream()
            .filter(roleRepresentation -> StringUtils.equalsIgnoreCase(roleRepresentation.getName(), role))
            .toList();

        if (!roles.isEmpty()) {
            keycloakAdminClient.setRoles(token, details.getId(), roles);
        }

        return details;
    }

    @Override
    public void resetPassword(User user, ChangePassword changePassword) {
        Assert.notNull("change password", changePassword);

        login(user.username(), changePassword.oldPassword()); // check if old password is correct (if not, an exception will be thrown)

        final OAuth2TokenResult tokenResult = authenticateClient();
        final String bearerToken = "bearer " + tokenResult.getAccessToken();
        keycloakAdminClient.resetPassword(bearerToken, user.id(), Credential.builder()
            .type("password")
            .value(changePassword.newPassword())
            .temporary(false)
            .build());
    }

    private OAuth2TokenResult authenticateClient() {
        return keycloakAuthFeignClient.refresh(UserLogin.builder()
            .client_id(keycloakClientConfig.getClient().getId())
            .client_secret(keycloakClientConfig.getClient().getSecret())
            .grant_type("client_credentials")
            .build());
    }

    private String formatAuthentication() {
        OAuth2TokenResult result = authenticateClient();

        return "Bearer " + result.getAccessToken();
    }
}
