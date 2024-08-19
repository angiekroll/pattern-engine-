/**
 * Copyright 2024, Company. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.domain.service;

import com.appgate.pattern_engine.domain.model.Subsequence;
import com.appgate.pattern_engine.domain.port.SubsequenceServicePort;
import org.springframework.stereotype.Service;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
@Service
public class SubsequenceService implements SubsequenceServicePort {


  @Override
  public Subsequence calculateSubsequences(String source, String target) {
    Subsequence subsequence = new Subsequence(source, target);
    return subsequence.calculateNumberSubsequences();
  }


}