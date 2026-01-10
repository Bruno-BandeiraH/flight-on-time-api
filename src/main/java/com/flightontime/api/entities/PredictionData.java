package com.flightontime.api.entities;


import com.flightontime.api.dto.PredictionRequestDTO;
import com.flightontime.api.dto.PredictionResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_predictions",
        indexes = {
                @Index(name = "idx_airline", columnList = "airline"),
                @Index(name = "idx_origin", columnList = "origin_airport"),
                @Index(name = "idx_destination", columnList = "destination_airport"),
                @Index(name = "idx_distance", columnList = "distance"),
                @Index(name = "idx_prediction", columnList = "prediction"),
                @Index(name = "idx_probability_of_delay", columnList = "probability")
        })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dados de Entrada (PredicitionRequestDTO)
    @Column(name = "airline", nullable = false, length = 10)
    private String icaoAirline;

    @Column(name = "origin_airport", nullable = false, length = 10)
    private String icaoOrigin;

    @Column(name = "destination_airport", nullable = false, length = 10)
    private String icaoDestination;

    @Column(name = "expected_time", nullable = false)
    private LocalDateTime expectedTime;

    @Column(name = "estimated_flight_time", nullable = false)
    private Float estimatedFlightTime;

    @Column(name = "distance", nullable = false)
    private Float distance;

    // Dados de Saída (PredictionResponseDTO)
    @Column(name = "prediction", nullable = false, length = 50)
    private Integer prediction;

    @Column(name = "probability", nullable = false)
    private Double probability;

    public static PredictionData fromDTOs(
            PredictionRequestDTO requestDTO,
            PredictionResponseDTO responseDTO) {

        return PredictionData.builder()
                .icaoAirline(requestDTO.icaoAirline())
                .icaoOrigin(requestDTO.icaoOrigin())
                .icaoDestination(requestDTO.icaoDestination())
                .expectedTime(requestDTO.expectedTime())
                .distance(requestDTO.distance())
                .estimatedFlightTime(requestDTO.estimatedFlightTime())
                .prediction(responseDTO.prediction())
                .probability(responseDTO.probability())
                .build();
    }


}
