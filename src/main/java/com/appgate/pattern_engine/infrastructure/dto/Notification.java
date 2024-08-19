/**
 * Copyright 2024, Company. All rights reserved Date: 14/07/24
 */
package com.appgate.pattern_engine.infrastructure.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Data
@Builder
public class Notification {

  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;

  public Notification(Instant timestamp, Integer status, String error, String message) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
  }

}