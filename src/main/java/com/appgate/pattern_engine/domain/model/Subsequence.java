/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.domain.model;

import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@AllArgsConstructor
@Getter
public class Subsequence {

  private final String source;
  private final String target;
  private int numberSubsequences;

  public Subsequence(String source, String target) {
    this.source = source;
    this.target = target;
  }

  public Subsequence calculateNumberSubsequences() {

    int sourceLength = source.length();
    int targetLength = target.length();

    int[][] matriz = new int[sourceLength + 1][targetLength + 1];

    IntStream.rangeClosed(0, sourceLength).forEach(i -> matriz[i][0] = 1);

    IntStream.range(1, sourceLength + 1)
        .forEach(sourceIndex -> IntStream.range(1, targetLength + 1)
            .forEach(targetIndex -> matriz[sourceIndex][targetIndex] =
                source.charAt(sourceIndex - 1) == target.charAt(targetIndex - 1)
                    ? matriz[sourceIndex - 1][targetIndex - 1] + matriz[sourceIndex
                    - 1][targetIndex]
                    : matriz[sourceIndex - 1][targetIndex]));

    numberSubsequences = matriz[sourceLength][targetLength];

    return this;

  }

}