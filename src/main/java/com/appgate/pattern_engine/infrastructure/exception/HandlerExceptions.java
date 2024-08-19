/**
 * Copyright 2024, Company. All rights reserved Date: 14/07/24
 */
package com.appgate.pattern_engine.infrastructure.exception;

import com.appgate.pattern_engine.infrastructure.constant.NotificationCode;
import com.appgate.pattern_engine.infrastructure.dto.Notification;
import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Slf4j
@RestControllerAdvice
public class HandlerExceptions {

  @ExceptionHandler(PatterEngineException.class)
  public ResponseEntity<Object> handlerPaymentPlatformException(PatterEngineException ex) {
    log.error("Exception: [{}]", ex.getErrorCode().getDescription(), ex);
    return new ResponseEntity<>(
        Notification.builder()
            .message(ex.getErrorCode().getDescription())
            .status(ex.getErrorCode().getHttpStatus().value())
            .error(ex.getErrorCode().getHttpStatus().getReasonPhrase())
            .timestamp(Instant.now())
            .build(),
        ex.getErrorCode().getHttpStatus());
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Notification> handlerMissingServletRequestParameterException(
      Exception ex) {
    log.error("Incorrect parameters: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Notification.builder()
            .message(
                "Incorrect parameters. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Notification> handlerConstraintViolationException(Exception ex) {
    log.error("Incorrect validations: {}", ex.getMessage());

   return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Notification.builder()
            .message("Incorrect validations. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Notification> handlerMethodArgumentNotValidException(Exception ex) {
    log.error("Incorrect validations: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Notification.builder()
            .message("Incorrect validations. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Notification> handlerException(Exception ex) {
    log.error("Unexpected exception: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Notification.builder()
            .message("Unexpected error occurred. " + ex.getMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }


}