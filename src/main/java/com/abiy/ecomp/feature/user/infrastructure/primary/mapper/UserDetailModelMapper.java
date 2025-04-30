package com.abiy.ecomp.feature.user.infrastructure.primary.mapper;

import com.abiy.ecomp.feature.user.infrastructure.primary.api.rest.v1.model.UserDetail;
import com.abiy.ecomp.shared.mapper.infrastructure.primary.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailModelMapper extends ModelMapper<UserDetail, com.abiy.ecomp.feature.user.domain.model.UserDetails> {
}
