package com.flightontime.api.controller;

import com.flightontime.api.dto.PredictionResponseDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import com.flightontime.api.service.PredictionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PredictionController.class)
@Import(com.flightontime.api.infra.exception.RestExceptionHandler.class)
class PredictionControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private PredictionService predictionService;
    @MockitoBean
    private List<RepositoryValidator> validators;

    @Test
    void shouldReturn200WhenRequestIsValid() throws Exception {
        when(predictionService.predict(any()))
            .thenReturn(buildResponse());

        mvc.perform(post("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.previsao").value("Pontual"))
            .andExpect(jsonPath("$.probabilidade").value(0.82));
    }

    @Test
    void shouldReturn400WhenBeanValidationFails() throws Exception {
        var invalidRequest = """
            {
              "icao_empresa_aerea": "",
              "icao_aerodromo_origem": "SBGR",
              "icao_aerodromo_destino": "SBRJ",
              "hora_prevista": "2025-11-12T22:30:00",
              "voos_no_slot": 18,
              "tempo_voo_estimado": 55
            }
            """;

        mvc.perform(post("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenCustomValidatorFails() throws Exception {
        Mockito.doThrow(new ValidatorException("icao_empresa_aerea", "Inválido"))
            .when(validators)
            .forEach(any());

        mvc.perform(post("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest()))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenJsonIsMalformed() throws Exception {
        mvc.perform(post("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid JSON"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn500WhenServiceThrowsException() throws Exception {
        Mockito.when(predictionService.predict(any()))
            .thenThrow(new RuntimeException("internal_error"));

        mvc.perform(post("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequest()))
            .andExpect(status().isInternalServerError());
    }

    private PredictionResponseDTO buildResponse() {
        return new PredictionResponseDTO(
            "Pontual",
            0.82
        );
    }

    private String validRequest() {
        return """
            {
              "icao_empresa_aerea": "AZ",
              "icao_aerodromo_origem": "SBGR",
              "icao_aerodromo_destino": "SBRJ",
              "hora_prevista": "2025-11-12T22:30:00",
              "voos_no_slot": 18,
              "tempo_voo_estimado": 55
            }
            """;
    }
}