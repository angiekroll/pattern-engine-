/**
 * Copyright 2024, Company. All rights reserved Date: 18/08/24
 */
package com.appgate.pattern_engine.infrastructure.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Valid
public class SubsequenceRequest {

  @NotBlank(message = "The 'source' is required and cannot be empty.")
  @Size(min = 1, max = 1000, message = ("The 'source' parameter cannot be more than 1000 characters."))
  @Pattern(regexp = "^[a-zA-Z]+$", message = "The 'source' parameter can only contain English letters.")
  private String source;

  @NotBlank(message = "The 'target' is required and cannot be empty.")
  @Size(max = 1000, message = ("The 'target' parameter cannot be more than 1000 characters."))
  @Pattern(regexp = "^[a-zA-Z]+$", message = "The 'target' parameter can only contain English letters.")
  private String target;

}