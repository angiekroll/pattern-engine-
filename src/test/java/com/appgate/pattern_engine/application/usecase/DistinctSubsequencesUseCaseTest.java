/**
 * Copyright 2024, Company. All rights reserved Date: 18/08/24
 */
package com.appgate.pattern_engine.application.usecase;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.appgate.pattern_engine.domain.model.Subsequence;
import com.appgate.pattern_engine.domain.port.SubsequenceServicePort;
import com.appgate.pattern_engine.infrastructure.dto.response.SubsequenceResponse;
import com.appgate.pattern_engine.infrastructure.exception.PatterEngineException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@ExtendWith(MockitoExtension.class)
class DistinctSubsequencesUseCaseTest {

  @InjectMocks
  private DistinctSubsequencesUseCase distinctSubsequencesUseCase;

  @Mock
  private SubsequenceServicePort subsequenceServicePort;

  @Test
  void shouldCallCalculateSubsequencesMethodWhenGivenValidSourceAndTarget()
      throws PatterEngineException {
    String sourceText = "rabbbit";
    String targetText = "rabbit";

    Mockito.when(subsequenceServicePort.calculateSubsequences(sourceText, targetText))
        .thenReturn(new Subsequence("babgbag", "bag", 3));

    distinctSubsequencesUseCase.countDistinctSubsequences(sourceText, targetText);

    Mockito.verify(subsequenceServicePort).calculateSubsequences(sourceText, targetText);

  }

  @Test
  void shouldReturnSubsequenceResponseDtoWhenSourceAndTargetAreValid()
      throws PatterEngineException {
    String sourceText = "rabbbit";
    String targetText = "rabbit";

    Mockito.when(subsequenceServicePort.calculateSubsequences(sourceText, targetText))
        .thenReturn(new Subsequence("rabbbit", "rabbit", 3));

    SubsequenceResponse responseDto = distinctSubsequencesUseCase.countDistinctSubsequences(
        sourceText, targetText);

    Assertions.assertEquals("rabbit", responseDto.getSubsequence());
    Assertions.assertEquals(3, responseDto.getNumberSubsequences());
  }

  @Test
  void shouldThrowExceptionWhenTargetLengthIsGreaterThanSourceLength() {
    String sourceText = "r";
    String targetText = "rabbit";

    assertThrows(PatterEngineException.class,
        () -> distinctSubsequencesUseCase.countDistinctSubsequences(sourceText, targetText));
  }


}