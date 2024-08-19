/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.application.usecase;

import com.appgate.pattern_engine.domain.port.SubsequenceServicePort;
import com.appgate.pattern_engine.infrastructure.constant.NotificationCode;
import com.appgate.pattern_engine.infrastructure.dto.response.SubsequenceResponseDto;
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
  public SubsequenceResponseDto countDistinctSubsequences(String sourceText, String targetText)
      throws PatterEngineException {

    log.debug("validating the length of the targetText: [{}]", targetText);
    if (targetText.length() > sourceText.length()) {
      throw new PatterEngineException(NotificationCode.INVALID_INPUT);
    }

    log.debug("Calculating the number of subsequences");
    log.info("Calculating the number of subsequences, sourceText: [{}], targetText: [{}]", sourceText, targetText);
    int numberSubsequence = subsequenceServicePort.calculateSubsequences(sourceText, targetText);

    log.debug("Number of subsequences is: [{}]", numberSubsequence);
    log.info("successful calculation");

    return SubsequenceResponseDto.builder()
        .subsequence(targetText)
        .numberSubsequences(numberSubsequence)
        .build();
  }

}