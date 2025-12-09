package com.flightontime.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {


    @PostMapping("/predict")
    public String getPrediction(String data) {
        return data;
    }
}
