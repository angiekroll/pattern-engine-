/**
 * Copyright 2024. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.application.usecase;

import com.appgate.pattern_engine.infrastructure.dto.SubsequenceDto;
import com.appgate.pattern_engine.infrastructure.exception.PatterEngineException;

/**
 *
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public interface DistinctSubsequencesUseCasePort {
  SubsequenceDto countDistinctSubsequences(String sourceText, String targetText) throws PatterEngineException;

}