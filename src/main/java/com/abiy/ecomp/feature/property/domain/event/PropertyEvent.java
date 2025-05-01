package com.abiy.ecomp.feature.property.domain.event;

import com.abiy.ecomp.feature.property.domain.model.Property;

public interface PropertyEvent {

    Property notify(Property property);
}
