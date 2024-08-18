/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.infrastructure.controller;

import com.appgate.pattern_engine.application.usecase.DistinctSubsequencesUseCasePort;
import com.appgate.pattern_engine.infrastructure.constant.ResourceMapping;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@RequestMapping(ResourceMapping.SUBSEQUENCES)
@RestController

public class SubsequencesController {

  private final DistinctSubsequencesUseCasePort idistinctSubsequencesUseCasePort;

  public SubsequencesController(
      DistinctSubsequencesUseCasePort idistinctSubsequencesUseCasePort) {
    this.idistinctSubsequencesUseCasePort = idistinctSubsequencesUseCasePort;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Integer> getDistinctSubsequences(
      @RequestParam String sourceText,
      @RequestParam String targetText) {
    return ResponseEntity.ok(
        idistinctSubsequencesUseCasePort.countDistinctSubsequences(sourceText, targetText));
  }
}