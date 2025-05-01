package com.abiy.ecomp.feature.property.infrastructure.primary.mapper;

import com.abiy.ecomp.feature.property.domain.model.Coordinates;
import com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1.model.LocationCoordinates;
import com.abiy.ecomp.shared.mapper.infrastructure.primary.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinateModelMapper extends ModelMapper<LocationCoordinates, Coordinates> {
}
