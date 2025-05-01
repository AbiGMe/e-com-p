package com.abiy.ecomp.feature.property.domain.model;

import com.abiy.ecomp.feature.property.domain.model.enums.PropertyType;

public record PropertyRequest(String title, String description, Location location, double price,
                              PropertyType propertyType, int bedrooms, int bathrooms, double size, boolean available) {
}