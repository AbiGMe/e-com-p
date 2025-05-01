package com.abiy.ecomp.feature.property.infrastructure.secondary.mapper;

import com.abiy.ecomp.feature.property.domain.model.Property;
import com.abiy.ecomp.shared.mapper.infrastructure.secondary.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        MediaItemMapper.class,
        LocationMapper.class
})
public interface PropertyMapper extends EntityMapper<Property, com.abiy.ecomp.feature.property.infrastructure.secondary.model.Property> {
}
