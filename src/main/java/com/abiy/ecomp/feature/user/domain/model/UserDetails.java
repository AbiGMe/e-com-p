package com.abiy.ecomp.feature.user.domain.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = -1608009883525411542L;

    private String id;

    private long createdTimestamp;

    private String username;

    private boolean enabled;

    private boolean totp;

    private boolean emailVerified;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> requiredActions;

    private int notBefore;

    private AccessDto access;

    public enum RequiredAction {
        UPDATE_PASSWORD
    }

    @Data
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccessDto implements Serializable {

        @Serial
        private static final long serialVersionUID = 7416126919078029231L;

        private boolean manageGroupMembership;

        private boolean view;

        private boolean mapRoles;

        private boolean impersonate;

        private boolean manage;
    }
}
