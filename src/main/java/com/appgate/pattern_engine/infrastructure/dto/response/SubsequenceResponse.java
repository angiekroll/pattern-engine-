/**
 * Copyright 2024, Company. All rights reserved Date: 18/08/24
 */
package com.appgate.pattern_engine.infrastructure.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubsequenceResponse {

  private String subsequence;
  private int numberSubsequences;

}