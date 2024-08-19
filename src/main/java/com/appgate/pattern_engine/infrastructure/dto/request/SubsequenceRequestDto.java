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
public class SubsequenceRequestDto {

  @NotBlank(message = "El 'source_text' es obligatorio y no puede estar vacío")
  @Size(min = 1, max = 1000, message = ("El parametro 'source' no puede tener más de 1000 caracteres."))
  @Pattern(regexp = "^[a-zA-Z]+$", message = "El parametro 'source' solo puede contener letras inglesas.")
  private String sourceText;

  @NotBlank(message = "El 'target_text' es obligatorio y no puede estar vacío")
  @Size(max = 1000, message = ("El parametro 'target' no puede tener más de 1000 caracteres."))
  @Pattern(regexp = "^[a-zA-Z]+$", message = "El parametro 'target' solo puede contener letras inglesas.")
  private String targetText;

}