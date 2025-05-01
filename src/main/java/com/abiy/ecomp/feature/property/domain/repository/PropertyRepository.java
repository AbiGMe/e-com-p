package com.abiy.ecomp.feature.property.domain.repository;

import com.abiy.ecomp.feature.property.domain.model.Property;
import com.abiy.ecomp.feature.property.domain.model.PropertySearchCriteria;
import com.abiy.ecomp.shared.pagination.domain.Page;
import com.abiy.ecomp.shared.pagination.domain.Pageable;

import java.util.Optional;

public interface PropertyRepository {

    Optional<Property> findById(String id);

    Page<Property> findAll(PropertySearchCriteria criteria, Pageable pageable);

    Page<Property> findAll(String searchTerm, Pageable pageable);

    Property save(Property property);

    void deleteById(String id);
}
