/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.infrastructure.adapter.in.controller;

import com.appgate.pattern_engine.application.usecase.DistinctSubsequencesUseCasePort;
import com.appgate.pattern_engine.infrastructure.constant.ResourceMapping;
import com.appgate.pattern_engine.infrastructure.dto.request.SubsequenceRequest;
import com.appgate.pattern_engine.infrastructure.dto.response.SubsequenceResponse;
import com.appgate.pattern_engine.infrastructure.exception.PatterEngineException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class SubsequenceController {

  private final DistinctSubsequencesUseCasePort idistinctSubsequencesUseCasePort;

  public SubsequenceController(
      DistinctSubsequencesUseCasePort idistinctSubsequencesUseCasePort) {
    this.idistinctSubsequencesUseCasePort = idistinctSubsequencesUseCasePort;
  }

  @Operation(summary = "Calculates the number of distinct subsequences of source that are equal to target")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = SubsequenceResponse.class))
      }),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
  })

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<SubsequenceResponse> getDistinctSubsequences(
      @Valid @RequestBody SubsequenceRequest subsequenceRequestDto,
      BindingResult resultErrors) throws PatterEngineException {

    log.debug("Entering the controller with sourceText: [{}], targetText: [{}]",
        subsequenceRequestDto.getSource(),
        subsequenceRequestDto.getTarget());

    return ResponseEntity.ok(
        idistinctSubsequencesUseCasePort.countDistinctSubsequences(
            subsequenceRequestDto.getSource(), subsequenceRequestDto.getTarget()));
  }


}