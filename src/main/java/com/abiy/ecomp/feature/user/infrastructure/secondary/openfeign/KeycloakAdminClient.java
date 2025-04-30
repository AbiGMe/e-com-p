package com.abiy.ecomp.feature.user.infrastructure.secondary.openfeign;

import com.abiy.ecomp.feature.user.domain.model.UserDetails;
import com.abiy.ecomp.feature.user.infrastructure.secondary.model.Credential;
import com.abiy.ecomp.feature.user.infrastructure.secondary.model.RoleRepresentation;
import com.abiy.ecomp.feature.user.infrastructure.secondary.model.UserRequestDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "keycloak-admin-client",
    url = "${keycloak.auth-server-url}",
    path = "/admin/realms/${keycloak.realm}",
    configuration = FeignClientsConfiguration.class)
public interface KeycloakAdminClient {

    @GetMapping(value = "/users")
    List<UserDetails> getUserByUsername(@RequestHeader(value = "Authorization") String bearerToken, @RequestParam(value = "username") String username, @RequestParam(value = "email") String email);

    @PutMapping(value = "/users/{id}/reset-password")
    void resetPassword(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable(value = "id") String id, @RequestBody @Valid Credential credential);

    @PutMapping(value = "/users/{id}/reset-password-email")
    void forgotPassword(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable(value = "id") String id);

    @GetMapping(value = "/users/{userId}")
    UserDetails getUser(@RequestHeader("Authorization") final String token, @PathVariable("userId") final String id);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    ResponseEntity<Boolean> registerUser(@RequestHeader("Authorization") final String token,
                                         @RequestBody final UserRequestDto user);

    @PostMapping(value = "/users/{id}/logout")
    void logout(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable(value = "id") String id);

    @PutMapping(value = "/users/{id}/send-verify-email")
    void sendVerifyEmail(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable(value = "id") String id);


    @PostMapping(value = "/users/{userId}/role-mappings/realm")
    ResponseEntity<?> setRoles(@RequestHeader("Authorization") final String token,
                               @PathVariable("userId") final String id,
                               @RequestBody final List<RoleRepresentation> roles);

    @GetMapping(value = "/users/{userId}/role-mappings/realm/available")
    List<RoleRepresentation> roles(@RequestHeader("Authorization") final String token,
                                   @PathVariable("userId") final String id);
}
