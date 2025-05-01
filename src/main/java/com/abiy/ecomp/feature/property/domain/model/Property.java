package com.abiy.ecomp.feature.property.domain.model;


import com.abiy.ecomp.feature.property.domain.model.enums.PropertyType;

import java.time.LocalDateTime;
import java.util.List;

public record Property(String id, String age, String title, String description, Location location, double price,
                       PropertyType propertyType, int bedrooms, int bathrooms, double size, boolean available,
                       LocalDateTime createdAt, LocalDateTime updatedAt, List<MediaItem> media) {
}
