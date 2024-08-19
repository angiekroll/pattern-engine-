/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.application.usecase;

import com.appgate.pattern_engine.application.mapper.SequenceMapper;
import com.appgate.pattern_engine.domain.model.Subsequence;
import com.appgate.pattern_engine.domain.port.SubsequenceServicePort;
import com.appgate.pattern_engine.infrastructure.constant.NotificationCode;
import com.appgate.pattern_engine.infrastructure.dto.response.SubsequenceResponse;
import com.appgate.pattern_engine.infrastructure.exception.PatterEngineException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Slf4j
@Component
public class DistinctSubsequencesUseCase implements DistinctSubsequencesUseCasePort {

  private final SubsequenceServicePort subsequenceServicePort;

  public DistinctSubsequencesUseCase(SubsequenceServicePort subsequenceServicePort) {
    this.subsequenceServicePort = subsequenceServicePort;
  }

  @Override
  public SubsequenceResponse countDistinctSubsequences(String source, String target)
      throws PatterEngineException {

    log.debug("validating the length of the targetText: [{}]", target);
    if (target.length() > source.length()) {
      throw new PatterEngineException(NotificationCode.INVALID_INPUT);
    }

    log.info("Calculating the number of subsequences, sourceText: [{}], targetText: [{}]", source,
        target);

    Subsequence subsequence = subsequenceServicePort.calculateSubsequences(source, target);

    log.debug("Number of subsequences is: [{}]", subsequence.getNumberSubsequences());
    log.info("successful calculation");

    return SequenceMapper.INSTANCE.toSubsequenceResponseDto(subsequence);

  }

}