package com.abiy.ecomp.feature.property.infrastructure.secondary.repository;

import com.abiy.ecomp.feature.property.domain.model.Property;
import com.abiy.ecomp.feature.property.domain.model.PropertySearchCriteria;
import com.abiy.ecomp.feature.property.domain.repository.PropertyRepository;
import com.abiy.ecomp.feature.property.infrastructure.secondary.jpa.PropertyJpaRepository;
import com.abiy.ecomp.feature.property.infrastructure.secondary.jpa.specs.PropertySearchSpec;
import com.abiy.ecomp.feature.property.infrastructure.secondary.jpa.specs.PropertySpec;
import com.abiy.ecomp.feature.property.infrastructure.secondary.mapper.PropertyMapper;
import com.abiy.ecomp.shared.pagination.domain.Page;
import com.abiy.ecomp.shared.pagination.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<Property> findAll(PropertySearchCriteria criteria, Pageable pageable) {

        String sortBy = CaseUtils.toCamelCase(StringUtils.getIfBlank(pageable.getSortBy(), () -> "created_date"), false, '_');
        Sort sort = pageable.getDirection() == Pageable.Direction.ASC ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        org.springframework.data.domain.Pageable pageRequest = PageRequest.of(pageable.getPage(), pageable.getPageSize(), sort);
        org.springframework.data.domain.Page<Property> properties = propertyJpaRepository.findAll(new PropertySpec(criteria), pageRequest)
                .map(propertyMapper::toBo);

        return propertyMapper.toBo(properties);
    }

    @Override
    public Page<Property> findAll(String searchTerm, Pageable pageable) {

        String sortBy = CaseUtils.toCamelCase(StringUtils.getIfBlank(pageable.getSortBy(), () -> "created_date"), false, '_');
        Sort sort = pageable.getDirection() == Pageable.Direction.ASC ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        org.springframework.data.domain.Pageable pageRequest = PageRequest.of(pageable.getPage(), pageable.getPageSize(), sort);
        org.springframework.data.domain.Page<Property> properties = propertyJpaRepository.findAll(new PropertySearchSpec(searchTerm), pageRequest)
                .map(propertyMapper::toBo);

        return propertyMapper.toBo(properties);
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
