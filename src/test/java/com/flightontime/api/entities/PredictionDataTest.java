package com.flightontime.api.entities;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PredictionDataTest {

    @Autowired
    private RepositoryPredictionData repositoryPredictionData;

    @BeforeEach
    void setUp() {
        repositoryPredictionData.deleteAll();

        PredictionData record1 = PredictionData.builder()
                .icaoAirline("GLO")
                .icaoOrigin("SBGR")
                .icaoDestination("SBBR")
                .expectedTime(LocalDateTime.of(2023, 5, 15, 14, 30))
                .slotFlights(5)
                .estimatedFlightTime(120)
                .prediction("pontual")
                .probability(0.92)
                .build();

        PredictionData record2 = PredictionData.builder()
                .icaoAirline("GLO")
                .icaoOrigin("SBGR")
                .icaoDestination("SBBR")
                .expectedTime(LocalDateTime.of(2023, 5, 16, 10, 0))
                .slotFlights(8)
                .estimatedFlightTime(125)
                .prediction("atrasado")
                .probability(0.78)
                .build();

        repositoryPredictionData.saveAll(List.of(record1, record2));
    }

    @Test
    void testFindAirlinesWithHighestOnTimeRate() {
        List<Object> result = repositoryPredictionData.findAirlinesWithHighestOnTimeRate();

        assertNotNull(result);

        System.out.println("==== Companhias =====");
        result.forEach(System.out::println);
    }

    @Test
    void testFindAirlinesWithHighestDelayRate() {
        List<Object> result = repositoryPredictionData.findAirlinesWithHighestDelayRate();

        assertNotNull(result);

        System.out.println("=== Companhias ===");
        result.forEach(System.out::println);
    }

    @Test
    void testFindRoutesWithHighestOnTimeRate() {
        List<Object> result = repositoryPredictionData.findRoutesWithHighestOnTimeRate();

        assertNotNull(result);

        System.out.println("=== Rotas ===");
        result.forEach(System.out::println);
    }

    @Test
    void testFindRoutesWithHighestDelayRate() {
        List<Object> result = repositoryPredictionData.findRoutesWithHighestDelayRate();

        assertNotNull(result);

        System.out.println("=== Rotas ===");
        result.forEach(System.out::println);
    }

    @Test
    void testGeneralStatistics() {
        Object result = repositoryPredictionData.getGeneralStatistics();

        assertNotNull(result);

        System.out.println("=== Estatísticas ===");
        System.out.println(result);
    }

    @Test
    void testGetStatisticsByYear() {
        List<Object> result = repositoryPredictionData.getStatisticsByYear();

        assertNotNull(result);

        System.out.println("=== Estatísticas ===");
        result.forEach(System.out::println);
    }

    @Test
    void testCountTotalPredictions() {
        long total = repositoryPredictionData.count();

        assertTrue(total > 0, "Deveria ter predições");

        System.out.println(" === total: " + total);
    }
}
