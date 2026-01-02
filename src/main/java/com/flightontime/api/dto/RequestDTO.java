package com.flightontime.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record RequestDTO(
    @JsonProperty("icao_empresa_aerea") String empresaAerea,
    @JsonProperty("icao_aerodromo_origem") String icaoOrigem,
    @JsonProperty("icao_aerodromo_destino") String icaoDestino,
    @JsonProperty("hora_prevista") LocalDateTime horaPrevista,
    @JsonProperty("voos_no_slot") Integer voosNoSlot,
    @JsonProperty("tempo_voo_estimado") Integer tempoDeVooEstimado
) {
}

//
//  "icao_empresa_aerea": "AZ",
//      "icao_aerodromo_origem": "SBGR",
//      "icao_aerodromo_destino": "SBRJ",
//      "hora_prevista": "2025-11-12T22:30:00",
//      "voos_no_slot": 18,
//      "tempo_voo_estimado": 55