package com.abiy.ecomp.feature.property.infrastructure.secondary.repository;

import com.abiy.ecomp.feature.property.domain.model.Property;
import com.abiy.ecomp.feature.property.domain.repository.PropertyRepository;
import com.abiy.ecomp.feature.property.infrastructure.secondary.jpa.PropertyJpaRepository;
import com.abiy.ecomp.feature.property.infrastructure.secondary.mapper.PropertyMapper;
import com.abiy.ecomp.shared.pagination.domain.Page;
import com.abiy.ecomp.shared.pagination.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private final PropertyMapper propertyMapper;
    private final PropertyJpaRepository propertyJpaRepository;

    @Override
    public Optional<Property> findById(String id) {
        return propertyJpaRepository.findById(id)
                .map(propertyMapper::toBo);
    }

    @Override
    public Page<Property> findAll(String searchTerm, Pageable pageable) {
        return null;
    }

    @Override
    public Property save(Property property) {
        return propertyMapper.toBo(propertyJpaRepository.save(propertyMapper.toEntity(property)));
    }

    @Override
    public void deleteById(String id) {
        propertyJpaRepository.deleteById(id);
    }
}
