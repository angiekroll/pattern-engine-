/**
 * Copyright 2024, Company. All rights reserved Date: 14/07/24
 */
package com.appgate.pattern_engine.infrastructure.exception;


import com.appgate.pattern_engine.infrastructure.constant.NotificationCode;
import lombok.Getter;

/**
 *
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public class PatterEngineException extends Exception {

  @Getter
  private final NotificationCode errorCode;

  public PatterEngineException(NotificationCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public PatterEngineException(NotificationCode errorCode, Throwable cause) {
    super(errorCode.getDescription(), cause);
    this.errorCode = errorCode;
  }

}