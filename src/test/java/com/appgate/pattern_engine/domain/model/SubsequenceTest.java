/**
 * Copyright 2024, Company. All rights reserved Date: 18/08/24
 */
package com.appgate.pattern_engine.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
class SubsequenceTest {

  @Test
  void shouldCalculateCorrectNumberOfSubsequences() {
    Subsequence subsequence = new Subsequence("rabbbit", "rabbit");
    Subsequence result = subsequence.calculateNumberSubsequences();
    assertEquals(3, result.getNumberSubsequences());
  }

  @Test
  void shouldCalculateCorrectNumberOfSubsequences_emptySourceAndTargetWithContent() {
    Subsequence subsequence = new Subsequence("", "rabbit");
    Subsequence result = subsequence.calculateNumberSubsequences();
    assertEquals(0, result.getNumberSubsequences());
  }

  @Test
  void shouldCalculateCorrectNumberOfSubsequences_bothSourceAndTargetEmpty() {
    Subsequence subsequence = new Subsequence("", "");
    Subsequence result = subsequence.calculateNumberSubsequences();
    assertEquals(1, result.getNumberSubsequences());
  }

  @Test
  void shouldCalculateCorrectNumberOfSubsequences_bothSourceAndTargetWithContent() {
    Subsequence subsequence = new Subsequence("b", "bag");
    Subsequence result = subsequence.calculateNumberSubsequences();
    assertEquals(0, result.getNumberSubsequences());
  }

}