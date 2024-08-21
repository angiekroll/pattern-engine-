/**
 * Copyright 2024, Company. All rights reserved Date: 14/07/24
 */
package com.appgate.pattern_engine.infrastructure.exception;

import com.appgate.pattern_engine.infrastructure.dto.response.NotificationError;
import com.appgate.pattern_engine.infrastructure.dto.response.ValidationError;
import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        NotificationError.builder()
            .message(ex.getErrorCode().getDescription())
            .status(ex.getErrorCode().getHttpStatus().value())
            .error(ex.getErrorCode().getHttpStatus().getReasonPhrase())
            .timestamp(Instant.now())
            .build(),
        ex.getErrorCode().getHttpStatus());
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<NotificationError> handlerMissingServletRequestParameterException(
      Exception ex) {
    log.error("Incorrect parameters: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(NotificationError.builder()
            .message(
                "Incorrect parameters. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<NotificationError> handleConstraintViolationException(ConstraintViolationException ex) {
    List<ValidationError> validationErrors = ex.getConstraintViolations().stream()
        .map(violation -> {
          String fieldName = violation.getPropertyPath().toString(); // Ejemplo: "getDistinctSubsequences.subsequenceRequestDto.source"
          String[] fieldParts = fieldName.split("\\.");
          String cleanFieldName = fieldParts[fieldParts.length - 1]; // Obtiene solo el nombre del campo, "source"
          return ValidationError.builder()
              .field(cleanFieldName)
              .message(violation.getMessage())
              .build();
        })
        .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(NotificationError.builder()
            .message("Validation failed") // Usamos el mensaje construido sin el nombre del método o DTO
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .errors(validationErrors) // Se añade la lista de errores detallados
            .timestamp(Instant.now())
            .build());
  }

 /* @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Notification> handlerMethodArgumentNotValidException(Exception ex) {
    log.error("Incorrect validations: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Notification.builder()
            .message("Incorrect validations. " + ex.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }*/

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> {
      // Solo agrega el mensaje de error sin incluir el campo o nombre del DTO
      errors.put(error.getField(), error.getDefaultMessage());
    });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<NotificationError> handlerException(Exception ex) {
    log.error("Unexpected exception: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(NotificationError.builder()
            .message("Unexpected error occurred. " + ex.getMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .timestamp(Instant.now())
            .build());
  }


}