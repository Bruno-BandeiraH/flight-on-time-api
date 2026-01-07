package com.flightontime.api;

import com.flightontime.api.entities.RepositoryPredictionData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PredictionDataTest {

    @Autowired
    private RepositoryPredictionData repositoryPredictionData;

    @Test
    void testFindAirlinesWithHighestOnTimeRate() {
        List<Object> result = repositoryPredictionData.findAirlinesWithHighestOnTimeRate();

        System.out.println("==== Companhias =====");
        result.forEach(System.out::println);
    }
}
