/**
 * Copyright 2024, Company. All rights reserved Date: 18/08/24
 */
package com.appgate.pattern_engine.domain.service;

import com.appgate.pattern_engine.domain.model.Subsequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@ExtendWith(MockitoExtension.class)
class SubsequenceServiceTest {

  @InjectMocks
  private SubsequenceService subsequenceService;


  @Test
  void testCalculateSubsequences_ValidInput() {
    String sourceText = "banana";
    String targetText = "an";

    Subsequence subsequence = subsequenceService.calculateSubsequences(sourceText, targetText);

    Assertions.assertEquals(3, subsequence.getNumberSubsequences());
  }


}