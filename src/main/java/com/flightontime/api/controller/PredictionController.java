package com.flightontime.api.controller;

import com.flightontime.api.dto.PredictionRequestDTO;
import com.flightontime.api.dto.PredictionResponseDTO;
import com.flightontime.api.infra.validations.RepositoryValidator;
import com.flightontime.api.service.PredictionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PredictionController {

    private final PredictionService predictionService;

    @Autowired
    private List<RepositoryValidator> validators;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public ResponseEntity<PredictionResponseDTO> getPrediction(@RequestBody @Valid PredictionRequestDTO data) {
        validators.forEach(v -> v.validator(data));
        PredictionResponseDTO response = predictionService.predict(data);
        return ResponseEntity.ok(response);
    }
}
