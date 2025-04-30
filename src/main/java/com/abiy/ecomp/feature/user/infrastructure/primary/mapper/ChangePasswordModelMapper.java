package com.abiy.ecomp.feature.user.infrastructure.primary.mapper;

import com.abiy.ecomp.feature.user.infrastructure.primary.api.rest.v1.model.ResetPasswordRequest;
import com.abiy.ecomp.shared.mapper.infrastructure.primary.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChangePasswordModelMapper extends ModelMapper<ResetPasswordRequest, com.abiy.ecomp.feature.user.domain.model.ChangePassword> {
}
