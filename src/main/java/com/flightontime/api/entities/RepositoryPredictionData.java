package com.flightontime.api.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPredictionData extends JpaRepository<PredictionData, Long> {

    /**
    * Retorna as companhias aéreas com maior taxa de voos PONTUAIS
     */
    @Query("""
        SELECT new map(
            f.icaoAirline as airline,
            COUNT(f) as totalFlights,
            SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) as onTimeFlights,
            (SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) as onTimePercentage,
            AVG(f.probability) as avgProbability
        )
        FROM PredictionData f
        GROUP BY f.icaoAirline
        HAVING COUNT(f) >= 1
        ORDER BY (SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) DESC
    """)
    List<Object> findAirlinesWithHighestOnTimeRate();


    /**
    * Retorna as rotas (origem-destino) com maior taxa de voos pontuais
     */
    @Query("""
        SELECT new map(
            f.icaoOrigin as originAirport,
            f.icaoDestination as destinationAirport,
            COUNT(f) as totalFlights,
            SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) as onTimeFlights,
            (SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) as onTimePercentage,
            AVG(f.estimatedFlightTime) as avgFlightTime
            )
            FROM PredictionData f
            GROUP BY f.icaoOrigin, f.icaoDestination
            HAVING COUNT(f) >= 1
            ORDER BY (SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) DESC
    """)
    List<Object> findRoutesWithHighestOnTimeRate();


    /**
    * Retorna as companhias aéreas com maior taxa de voos atrasados
     */
    @Query("""
        SELECT new map(
            f.icaoAirline as airline,
            COUNT(f) as totalFlights,
            SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) as delayedFlights,
            (SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) as delayPercentage,
            AVG(f.probability) as avgProbability
        )
        FROM PredictionData f
        GROUP BY f.icaoAirline
        HAVING COUNT(f) >= 1
        ORDER BY (SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) DESC
    """)
    List<Object> findAirlinesWithHighestDelayRate();

    /**
    * Retorna as rotas (origem-destino) com maior taxa de voos atrasados
     */
    @Query("""
        SELECT new map(
            f.icaoOrigin as originAirport,
            f.icaoDestination as destinationAirport,
            COUNT(f) as totalFlights,
            SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) as delayedFlights,
            (SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) as delayPercentage,
            AVG(f.estimatedFlightTime) as avgFlightTime
        )
        FROM PredictionData f
        GROUP BY f.icaoOrigin, f.icaoDestination
        HAVING COUNT(f) >= 1
        ORDER BY (SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) DESC
    """)
    List<Object> findRoutesWithHighestDelayRate();

    /**
    * Estatísticas gerais
     */
    @Query("""
        SELECT new map(
            COUNT(f) as totalPredictions,
            SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) as totalOnTime,
            SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) as totalDelay,
            AVG(f.probability) as avgProbability,
            AVG(f.estimatedFlightTime) as avgEstimatedTime
        )
        FROM PredictionData f
    """)
    Object getGeneralStatistics();

    /**
    * Estatisticas por ano
     */
    @Query("""
        SELECT new map(
            YEAR(f.expectedTime) as year,
            COUNT(f) as totalPredictions,
            SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) as totalOnTime,
            SUM(CASE WHEN f.prediction = 'Atrasado' THEN 1 ELSE 0 END) as totalDelayed,
            (SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) as onTimePercentage
        )
        FROM PredictionData f
        GROUP BY YEAR(f.expectedTime)
        ORDER BY YEAR(f.expectedTime) DESC
        """)
    List<Object> getStatisticsByYear();

    /**
    * Top companhias por ano específico
     */
    @Query("""
        SELECT new map(
            f.icaoAirline as airline,
            COUNT(f) as totalFlights,
            SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) as onTimeFlights,
            (SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) as onTimePercentage
        )
        FROM PredictionData f
        WHERE YEAR(f.expectedTime) = :year
        GROUP BY f.icaoAirline
        HAVING COUNT(f) >= 10
        ORDER BY (SUM(CASE WHEN f.prediction = 'Pontual' THEN 1 ELSE 0 END) * 100.0 / COUNT(f)) DESC
    """)
    List<Object> findAirlinesWithHighestOnTimeRateByYear(@Param("year") int year);

}
