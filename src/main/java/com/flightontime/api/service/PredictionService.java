package com.flightontime.api.service;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.dto.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PredictionService {

    private final WebClient predictionWebClient;


    public PredictionService(WebClient predictionWebClient) {
        this.predictionWebClient = predictionWebClient;
    }

    public ResponseDTO predict(RequestDTO request) {
        return predictionWebClient.post()
            .uri("/predict")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(ResponseDTO.class)
            .block();
    }
}
