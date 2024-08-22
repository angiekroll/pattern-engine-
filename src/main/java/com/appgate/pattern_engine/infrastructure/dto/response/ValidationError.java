/**
 * Copyright 2024, Company. All rights reserved Date: 21/08/24
 */
package com.appgate.pattern_engine.infrastructure.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Data
@Builder
public class ValidationError {

  private String field;
  private String message;

}