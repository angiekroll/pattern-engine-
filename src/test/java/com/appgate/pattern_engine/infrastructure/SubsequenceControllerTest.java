/**
 * Copyright 2024, Company. All rights reserved Date: 18/08/24
 */
package com.appgate.pattern_engine.infrastructure;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.appgate.pattern_engine.application.usecase.DistinctSubsequencesUseCasePort;
import com.appgate.pattern_engine.infrastructure.dto.request.SubsequenceRequest;
import com.appgate.pattern_engine.infrastructure.dto.response.SubsequenceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@SpringBootTest
@AutoConfigureMockMvc
class SubsequenceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DistinctSubsequencesUseCasePort idistinctSubsequencesUseCasePort;


  @Test
  void shouldReturnSubsequenceCountWhenRequestIsValid() throws Exception {

    String requetsJsonString = new ObjectMapper().writeValueAsString(SubsequenceRequestDto());

    Mockito.when(idistinctSubsequencesUseCasePort.countDistinctSubsequences(
            SubsequenceRequestDto().getSource(), SubsequenceRequestDto().getTarget()))
        .thenReturn(builSubsequenceResponseDto());

    mockMvc.perform(MockMvcRequestBuilders.post("/subsequences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requetsJsonString))
        .andExpect(status().isOk())

        .andExpect(MockMvcResultMatchers.jsonPath("$.subsequence").value("an"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.number_subsequences").value(3));

  }


  @Test
  void shouldReturnBadRequestWhenRequestIsMissingTargetText() throws Exception {

    String requetsJsonString = new ObjectMapper().writeValueAsString(
        SubsequenceRequest.builder().source("banana").build());

    mockMvc.perform(MockMvcRequestBuilders.post("/subsequences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requetsJsonString))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Validation failed"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].field").value("target"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message")
            .value("The 'target' is required and cannot be empty."));
  }

  @Test
  void shouldReturnBadRequestWhenRequestIsMissingSourceText() throws Exception {

    String requetsJsonString = new ObjectMapper().writeValueAsString(
        SubsequenceRequest.builder().target("rabbit").build());

    mockMvc.perform(MockMvcRequestBuilders.post("/subsequences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requetsJsonString))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(
            "Validation failed"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].field").value("source"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message")
            .value("The 'source' is required and cannot be empty."));
  }

  @Test
  void shouldFailWhenSourceTextExceeds1000Characters() throws Exception {

    String requetsJsonString = new ObjectMapper().writeValueAsString(
        SubsequenceRequest.builder().source(
                "rabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrabbbitrzzzzzz")
            .target("rabbit").build());

    mockMvc.perform(MockMvcRequestBuilders.post("/subsequences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requetsJsonString))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(
            "Validation failed"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].field").value("source"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message")
            .value("The 'source' parameter cannot be more than 1000 characters."));
  }

  @Test
  void shouldFailWhenRequestDoesNotHaveEnglishLetters() throws Exception {

    String requetsJsonString = new ObjectMapper().writeValueAsString(
        SubsequenceRequest.builder().source("ñ")
            .target("rabbit").build());

    mockMvc.perform(MockMvcRequestBuilders.post("/subsequences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requetsJsonString))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(
            "Validation failed"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].field").value("source"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message")
            .value("The 'source' parameter can only contain English letters."));

  }

  SubsequenceResponse builSubsequenceResponseDto() {
    return SubsequenceResponse.builder()
        .subsequence("an")
        .numberSubsequences(3)
        .build();
  }

  SubsequenceRequest SubsequenceRequestDto() {
    return SubsequenceRequest.builder()
        .source("babgbag")
        .target("bag")
        .build();
  }

}