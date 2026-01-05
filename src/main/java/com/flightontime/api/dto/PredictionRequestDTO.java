package com.flightontime.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PredictionRequestDTO(
    @JsonProperty("icao_empresa_aerea") String icaoAirline,
    @JsonProperty("icao_aerodromo_origem") String icaoOrigin,
    @JsonProperty("icao_aerodromo_destino") String icaoDestination,
    @JsonProperty("hora_prevista") LocalDateTime expectedTime,
    @JsonProperty("voos_no_slot") Integer slotFlights,
    @JsonProperty("tempo_voo_estimado") Integer estimatedFlightTime
) {
}

