package com.abiy.ecomp.feature.property.domain.model;

import java.math.BigDecimal;

public record PropertySearchCriteria(String title, BigDecimal minPrice, BigDecimal maxPrice, Integer bedrooms,
                                     Integer bathrooms, Boolean available) {
    public boolean hasFilters() {
        return title != null || minPrice != null || maxPrice != null || bedrooms != null || bathrooms != null || available != null;
    }
}
