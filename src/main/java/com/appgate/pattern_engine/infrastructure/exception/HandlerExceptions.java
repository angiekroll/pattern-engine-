/**
 * Copyright 2024, Company. All rights reserved Date: 14/07/24
 */
package com.appgate.pattern_engine.infrastructure.exception;

import com.appgate.pattern_engine.infrastructure.constant.NotificationCode;
import com.appgate.pattern_engine.infrastructure.dto.NotificationDto;
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
        NotificationDto.builder()
            .message(ex.getErrorCode().getDescription())
            .status(ex.getErrorCode().getHttpStatus().value())
            .error(ex.getErrorCode().getHttpStatus().getReasonPhrase())
            .timestamp(Instant.now())
            .build(),
        ex.getErrorCode().getHttpStatus());
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<NotificationDto> handlerMissingServletRequestParameterException(
      Exception ex) {
    log.error("Incorrect parameters: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(NotificationDto.builder()
            .message(
                "Incorrect parameters. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<NotificationDto> handlerConstraintViolationException(Exception ex) {
    log.error("Validaciones incorrectas: {}", ex.getMessage());

   return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(NotificationDto.builder()
            .message("Validaciones incorrectas. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  public static void validateInputFields(BindingResult resultErrors) throws PatterEngineException {
    if (resultErrors.hasErrors()) {
      List<FieldError> fieldErrors = resultErrors.getFieldErrors();
      List<String> errorMessages = fieldErrors.stream()
          .map(error -> error.getField() + ": " + error.getDefaultMessage())
          .toList();
      throw new PatterEngineException(NotificationCode.INVALID_INPUT,
          new Throwable(errorMessages.toString()));
    }
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<NotificationDto> handlerMethodArgumentNotValidException(Exception ex) {
    log.error("Validaciones incorrectas: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(NotificationDto.builder()
            .message("Validaciones incorrectas. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<NotificationDto> handlerException(Exception ex) {
    log.error("Unexpected exception: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(NotificationDto.builder()
            .message("Unexpected error occurred. " + ex.getMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  // MethodArgumentNotValidException

}