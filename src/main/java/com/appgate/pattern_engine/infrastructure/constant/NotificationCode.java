/**
 * Copyright 2024, Company. All rights reserved Date: 14/07/24
 */
package com.appgate.pattern_engine.infrastructure.constant;

import org.springframework.http.HttpStatus;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public enum NotificationCode {
  INVALID_INPUT(
      "The length of the target text cannot be longer than the source text. Please verify the values entered.",
      HttpStatus.BAD_REQUEST);


  private final HttpStatus httpStatus;
  private final String message;


  NotificationCode(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public String getDescription() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}