/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.infrastructure.controller;

import com.appgate.pattern_engine.application.usecase.DistinctSubsequencesUseCasePort;
import com.appgate.pattern_engine.infrastructure.constant.ResourceMapping;
import com.appgate.pattern_engine.infrastructure.dto.SubsequenceDto;
import com.appgate.pattern_engine.infrastructure.exception.PatterEngineException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Slf4j
@Validated
@RequestMapping(ResourceMapping.SUBSEQUENCES)
@RestController
public class SubsequencesController {

  private final DistinctSubsequencesUseCasePort idistinctSubsequencesUseCasePort;

  public SubsequencesController(
      DistinctSubsequencesUseCasePort idistinctSubsequencesUseCasePort) {
    this.idistinctSubsequencesUseCasePort = idistinctSubsequencesUseCasePort;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<SubsequenceDto> getDistinctSubsequences(
      @Valid @NotNull @Size(min = 1, max = 1000, message = ("El parametro 'source' no puede tener más de 1000 caracteres."))
      @Pattern(regexp = "^[a-zA-Z]+$", message = "El parametro 'source' solo puede contener letras inglesas.")
      @RequestParam("source") String sourceText,

      @Valid @NotNull @Size(max = 1000, message = ("El parametro 'target' no puede tener más de 1000 caracteres."))
      @Pattern(regexp = "^[a-zA-Z]+$", message = "El parametro 'target' solo puede contener letras inglesas.")
      @RequestParam("target") String targetText) throws PatterEngineException {

    log.debug("sourceText: [{}], targetText: [{}]", sourceText, targetText);

    return ResponseEntity.ok(
        idistinctSubsequencesUseCasePort.countDistinctSubsequences(sourceText, targetText));
  }
}