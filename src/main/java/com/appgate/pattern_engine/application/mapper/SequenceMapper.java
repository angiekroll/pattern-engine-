/**
 * Copyright 2024. All rights reserved Date: 18/08/24
 */
package com.appgate.pattern_engine.application.mapper;

import com.appgate.pattern_engine.domain.model.Subsequence;
import com.appgate.pattern_engine.infrastructure.dto.response.SubsequenceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SequenceMapper {

  SequenceMapper INSTANCE = Mappers.getMapper(SequenceMapper.class);

  @Mapping(source = "target", target = "subsequence")
  SubsequenceResponse toSubsequenceResponseDto(Subsequence subsequence);


}
