package com.abiy.ecomp.feature.property.domain.model;


import com.abiy.ecomp.feature.property.domain.model.enums.PropertyStatus;
import com.abiy.ecomp.feature.property.domain.model.enums.PropertyType;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public record Property(String id, String userId, String age, String title, String description, Location location, double price,
                       PropertyType propertyType, int bedrooms, int bathrooms, double size, boolean available,
                       LocalDateTime createdAt, LocalDateTime updatedAt, List<MediaItem> media, PropertyStatus status) {


    public static Property from(PropertyRequest request, String userId) {
        return new Property(null, userId, null, request.title(), request.description(), request.location(), request.price(), request.propertyType(), request.bedrooms(), request.bathrooms(), request.size(), request.available(), LocalDateTime.now(), null, null, PropertyStatus.DRAFTED);
    }

    public Property update(PropertyRequest request) {
        return new Property(id, userId, age, request.title(), request.description(), request.location(), request.price(), request.propertyType(), request.bedrooms(), request.bathrooms(), request.size(), request.available(), createdAt, LocalDateTime.now(),     // Update timestamp
                media, status);
    }

    public Property partialUpdate(PropertyRequest request) {
        return new Property(id, userId, age, StringUtils.isNotBlank(request.title()) ? request.title() : title, StringUtils.isNotBlank(request.description()) ? request.description() : description, request.location() != null ? request.location() : location, request.price() > 0 ? request.price() : price, request.propertyType() != null ? request.propertyType() : propertyType, request.bedrooms() > 0 ? request.bedrooms() : bedrooms, request.bathrooms() > 0 ? request.bathrooms() : bathrooms, request.size() > 0 ? request.size() : size, request.available(), createdAt, LocalDateTime.now(), media, status);
    }
}
