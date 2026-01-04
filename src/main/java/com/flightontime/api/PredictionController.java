package com.flightontime.api;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.dto.ResponseDTO;
import com.flightontime.api.infra.validations.RepositoryValidator;
import com.flightontime.api.infra.validations.airline.AirlineValidator;
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
    public ResponseEntity<ResponseDTO> getPrediction(@RequestBody RequestDTO data) {
        validators.forEach(v -> v.validator(data));
        ResponseDTO response = predictionService.predict(data);
        return ResponseEntity.ok(response);
    }
}
