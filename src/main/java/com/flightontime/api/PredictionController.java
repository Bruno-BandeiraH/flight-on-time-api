package com.flightontime.api;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.dto.ResponseDTO;
import com.flightontime.api.service.PredictionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public ResponseEntity<ResponseDTO> getPrediction(@RequestBody @Valid RequestDTO data) {
        ResponseDTO response = predictionService.predict(data);
        return ResponseEntity.ok(response);
    }
}
