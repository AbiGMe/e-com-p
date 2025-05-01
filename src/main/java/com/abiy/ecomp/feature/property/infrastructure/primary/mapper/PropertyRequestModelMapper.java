package com.abiy.ecomp.feature.property.infrastructure.primary.mapper;

import com.abiy.ecomp.feature.property.domain.model.PropertyRequest;
import com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1.model.PropertyCreate;
import com.abiy.ecomp.shared.mapper.infrastructure.primary.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyRequestModelMapper extends ModelMapper<PropertyCreate, PropertyRequest> {
}
