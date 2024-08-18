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

  public Subsequence(String sourceText, String targetText) {
    this.sourceText = sourceText;
    this.targetText = targetText;
  }

  public Subsequence() {
  }

  public int calculateNumberSubsequences() {

    int sourceLength = sourceText.length();
    int targetLength = targetText.length();

    int[][] matriz = new int[sourceLength + 1][targetLength + 1];

    IntStream.rangeClosed(0, sourceLength).forEach(i -> matriz[i][0] = 1);

    IntStream.range(1, sourceLength + 1)
        .forEach(sourceTextIndex -> IntStream.range(1, targetLength + 1)
            .forEach(targetTextIndex -> matriz[sourceTextIndex][targetTextIndex] =
                sourceText.charAt(sourceTextIndex - 1) == targetText.charAt(targetTextIndex - 1)
                    ? matriz[sourceTextIndex - 1][targetTextIndex - 1] + matriz[sourceTextIndex
                    - 1][targetTextIndex]
                    : matriz[sourceTextIndex - 1][targetTextIndex]));

    return matriz[sourceLength][targetLength];

  }

}