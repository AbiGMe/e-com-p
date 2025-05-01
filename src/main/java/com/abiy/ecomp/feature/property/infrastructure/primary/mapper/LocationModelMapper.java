package com.abiy.ecomp.feature.property.infrastructure.primary.mapper;

import com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1.model.Location;
import com.abiy.ecomp.shared.mapper.infrastructure.primary.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CoordinateModelMapper.class)
public interface LocationModelMapper extends ModelMapper<Location, com.abiy.ecomp.feature.property.domain.model.Location> {
}
