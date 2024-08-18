/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.application.usecase;

import com.appgate.pattern_engine.domain.port.SubsequenceServicePort;
import org.springframework.stereotype.Component;

/**
 *
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Component
public class DistinctSubsequencesUseCase implements DistinctSubsequencesUseCasePort {

  private final SubsequenceServicePort subsequenceServicePort;

  public DistinctSubsequencesUseCase(SubsequenceServicePort subsequenceServicePort) {
    this.subsequenceServicePort = subsequenceServicePort;
  }

  @Override
  public int countDistinctSubsequences(String sourceText, String targetText) {
    return subsequenceServicePort.calculateSubsequences(sourceText, targetText);
  }

}