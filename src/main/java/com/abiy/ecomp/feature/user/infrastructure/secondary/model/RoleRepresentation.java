package com.abiy.ecomp.feature.user.infrastructure.secondary.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RoleRepresentation {

    private Map<String, Object> attributes;

    private boolean clientRole;

    private boolean composite;

    private String containerId;

    private String description;

    private String id;

    private String name;
}

