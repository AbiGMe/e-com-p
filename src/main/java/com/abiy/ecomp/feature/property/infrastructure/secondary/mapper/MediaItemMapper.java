package com.abiy.ecomp.feature.property.infrastructure.secondary.mapper;

import com.abiy.ecomp.feature.property.domain.model.MediaItem;
import com.abiy.ecomp.shared.mapper.infrastructure.secondary.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaItemMapper extends EntityMapper<MediaItem, com.abiy.ecomp.feature.property.infrastructure.secondary.model.MediaItem> {
}
