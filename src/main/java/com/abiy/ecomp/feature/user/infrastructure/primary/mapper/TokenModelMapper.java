package com.abiy.ecomp.feature.user.infrastructure.primary.mapper;

import com.abiy.ecomp.feature.user.domain.model.OAuth2TokenResult;
import com.abiy.ecomp.feature.user.infrastructure.primary.api.rest.v1.model.Token;
import com.abiy.ecomp.shared.mapper.infrastructure.primary.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenModelMapper extends ModelMapper<Token, OAuth2TokenResult> {
}
