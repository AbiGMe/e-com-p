package com.abiy.ecomp.feature.property.domain.event;

import com.abiy.ecomp.feature.property.domain.model.Property;

public interface PropertyEvent {

    default Property notify(Property property) {
        return property;
    }
}
