/**
 * Copyright 2024. All rights reserved Date: 17/08/24
 */
package com.appgate.pattern_engine.domain.port;

import com.appgate.pattern_engine.domain.model.Subsequence;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public interface SubsequenceServicePort {

  Subsequence calculateSubsequences(String source, String target);

}