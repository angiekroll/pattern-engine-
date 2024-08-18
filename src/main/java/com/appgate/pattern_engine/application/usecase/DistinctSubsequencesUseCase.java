/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.application.usecase;

import com.appgate.pattern_engine.domain.port.SubsequenceServicePort;
import com.appgate.pattern_engine.infrastructure.constant.NotificationCode;
import com.appgate.pattern_engine.infrastructure.dto.SubsequenceDto;
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
  public SubsequenceDto countDistinctSubsequences(String sourceText, String targetText)
      throws PatterEngineException {

    if (targetText.length() > sourceText.length()) {
      log.error("Error getting response from bank external API");
      throw new PatterEngineException(NotificationCode.INVALID_INPUT);
    }

    int numberSubsequence = subsequenceServicePort.calculateSubsequences(sourceText, targetText);

    return SubsequenceDto.builder()
        .subsequence(targetText)
        .numberSubsequences(numberSubsequence)
        .build();
  }

}