package com.flightontime.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record PredictionRequestDTO(
    @JsonProperty("icao_empresa_aerea")
    @NotBlank(message = "icao_empresa_aerea é obrigatória")
    @Pattern(regexp = "[A-Z]{2}", message = "O campo deve conter 2 letras maiúsculas")
    String icaoAirline,

    @JsonProperty("icao_aerodromo_origem")
    @NotBlank(message = "icao_aerodromo_origem é obrigatório")
    @Pattern(regexp = "[A-Z]{4}", message = "O campo deve conter 4 letras maiúsculas")
    String icaoOrigin,

    @JsonProperty("icao_aerodromo_destino")
    @NotBlank(message = "icao_aerodromo_destino é obrigatório")
    @Pattern(regexp = "[A-Z]{4}", message = "O campo deve conter 4 letras maiúsculas")
    String icaoDestination,

    @JsonProperty("hora_prevista")
    @NotNull(message = "hora_prevista não pode ser nula")
    LocalDateTime expectedTime,

    @JsonProperty("voos_no_slot")
    @NotNull(message = "voos_no_slot não pode ser nulo")
    @Min(value = 1, message = "A quantidade de voos no slot não pode ser negativa")
    Integer slotFlights,

    @JsonProperty("tempo_voo_estimado")
    @NotNull(message = "tempo_voo_estimado não pode ser nulo")
    @Min(value = 20, message = "o tempo de voo não pode ser menor que 20 minutos")
    @Max(value = 420, message = "O tempo de voo não pode ser superior a 7 horas")
    Integer estimatedFlightTime
) {
}

