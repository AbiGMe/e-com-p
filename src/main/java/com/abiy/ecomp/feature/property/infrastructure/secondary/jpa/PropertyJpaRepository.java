package com.abiy.ecomp.feature.property.infrastructure.secondary.jpa;

import com.abiy.ecomp.feature.property.infrastructure.secondary.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyJpaRepository extends JpaRepository<Property, String>, JpaSpecificationExecutor<Property> {
}
