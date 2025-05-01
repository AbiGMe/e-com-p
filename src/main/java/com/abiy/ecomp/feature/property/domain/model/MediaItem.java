package com.abiy.ecomp.feature.property.domain.model;

import com.abiy.ecomp.feature.property.domain.model.enums.MediaType;

import java.time.LocalDateTime;

public record MediaItem(String id, String url, MediaType type, LocalDateTime createdAt) {
}
