package com.abiy.ecomp.feature.property.infrastructure.secondary.mapper;

import com.abiy.ecomp.feature.property.domain.model.Coordinates;
import com.abiy.ecomp.shared.mapper.infrastructure.secondary.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinateMapper extends EntityMapper<Coordinates, com.abiy.ecomp.feature.property.infrastructure.secondary.model.Coordinates> {
}
