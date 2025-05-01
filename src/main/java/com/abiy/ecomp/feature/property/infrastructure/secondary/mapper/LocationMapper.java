package com.abiy.ecomp.feature.property.infrastructure.secondary.mapper;

import com.abiy.ecomp.feature.property.domain.model.Location;
import com.abiy.ecomp.shared.mapper.infrastructure.secondary.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CoordinateMapper.class)
public interface LocationMapper extends EntityMapper<Location, com.abiy.ecomp.feature.property.infrastructure.secondary.model.Location> {
}
