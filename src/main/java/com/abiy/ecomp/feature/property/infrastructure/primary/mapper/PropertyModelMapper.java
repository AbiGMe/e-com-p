package com.abiy.ecomp.feature.property.infrastructure.primary.mapper;

import com.abiy.ecomp.feature.ai.infrastructure.primary.api.rest.v1.model.Property;
import com.abiy.ecomp.shared.mapper.infrastructure.primary.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LocationModelMapper.class)
public interface PropertyModelMapper extends ModelMapper<Property, com.abiy.ecomp.feature.property.domain.model.Property> {
}
