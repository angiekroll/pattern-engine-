/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.domain.model;

import java.util.stream.IntStream;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public class Subsequence {

  private String sourceText;
  private String targetText;
  private int count = 0;

  public Subsequence(String sourceText, String targetText) {
    this.sourceText = sourceText;
    this.targetText = targetText;
  }

  public Subsequence() {
  }

  public int getCount() {
    return count;
  }

  public int calculateCount() {

    int sourceLength = sourceText.length();

    this.count = (int) IntStream.rangeClosed(1, sourceLength)
        .mapToObj(i -> sourceText.substring(0, i))
        .filter(subsequence -> isSubsequence(subsequence, targetText))
        .count();

    return count;
  }

  private boolean isSubsequence(String subsequence, String targetText) {
    int subsequenceIndex = 0;
    int targetTexIndex = 0;
    while (subsequenceIndex < subsequence.length() && targetTexIndex < targetText.length()) {
      if (subsequence.charAt(subsequenceIndex) == targetText.charAt(targetTexIndex)) {
        subsequenceIndex++;
        targetTexIndex++;
      } else {
        targetTexIndex++;
      }
    }
    return subsequenceIndex == subsequence.length();
  }
}