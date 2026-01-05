package com.flightontime.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PredictionResponseDTO(

    @JsonProperty("previsao") String prediction,
    @JsonProperty("probabilidade") Double probability
) {
}
