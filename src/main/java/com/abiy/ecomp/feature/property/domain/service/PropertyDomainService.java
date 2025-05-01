package com.abiy.ecomp.feature.property.domain.service;

import com.abiy.ecomp.feature.property.domain.event.PropertyEvent;
import com.abiy.ecomp.feature.property.domain.exceptions.PropertyErrorKey;
import com.abiy.ecomp.feature.property.domain.model.Property;
import com.abiy.ecomp.feature.property.domain.model.PropertyRequest;
import com.abiy.ecomp.feature.property.domain.model.PropertySearchCriteria;
import com.abiy.ecomp.feature.property.domain.repository.PropertyRepository;
import com.abiy.ecomp.shared.authentication.application.AuthenticatedUser;
import com.abiy.ecomp.shared.error.domain.Assert;
import com.abiy.ecomp.shared.error.domain.GeneratorException;
import com.abiy.ecomp.shared.pagination.domain.Page;
import com.abiy.ecomp.shared.pagination.domain.Pageable;

public class PropertyDomainService {

    public final PropertyRepository propertyRepository;
    public final PropertyEvent propertyEvent;

    public PropertyDomainService(PropertyRepository propertyRepository, PropertyEvent propertyEvent) {
        this.propertyRepository = propertyRepository;
        this.propertyEvent = propertyEvent;
    }

    public Property findById(String id) {

        Assert.notBlank("Property id", id);

        return propertyRepository.findById(id)
                .orElseThrow(() -> GeneratorException.notFound(PropertyErrorKey.PROPERTY_NOT_FOUND).message("Property not found").build());
    }

    public Page<Property> findAll(PropertySearchCriteria criteria, Pageable paginated) {

        Assert.notNull("Property criteria", criteria);
        Assert.notNull("Property paginated", paginated);

        return propertyRepository.findAll(criteria, paginated);
    }

    public Page<Property> findAll(String searchTerm, Pageable paginated) {

        Assert.notNull("Property paginated", paginated);

        return propertyRepository.findAll(searchTerm, paginated);
    }

    public Property create(PropertyRequest propertyRequest) {

        Assert.notNull("Property request", propertyRequest);

        // TODO: check for the user

        return propertyEvent.notify(propertyRepository.save(Property.from(propertyRequest, AuthenticatedUser.getUser().id())));
    }

    public Property update(String id, PropertyRequest propertyRequest) {

        Assert.notNull("Property id", id);
        Assert.notNull("Property request", propertyRequest);

        // TODO: check for the properties user

        return propertyRepository.findById(id)
                .map(property -> property.partialUpdate(propertyRequest))
                .map(propertyRepository::save)
                .map(propertyEvent::notify)
                .orElseThrow(() -> GeneratorException.notFound(PropertyErrorKey.PROPERTY_NOT_FOUND).message("Property not found").build());
    }

    public void deleteById(String id) {
        Assert.notBlank("Property id", id);

        // TODO: check for the properties user

        propertyRepository.deleteById(id);
    }
}
