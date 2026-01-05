package com.flightontime.api.infra.validations.time;

import com.flightontime.api.dto.PredictionRequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class EstimatedFlightTime implements RepositoryValidator {
    @Override
    public void validator(PredictionRequestDTO data) {
        if (data.estimatedFlightTime() == null) {
            throw new ValidatorException("tempo_voo_estimado", "O tempo de voo não pode ser nulo");
        }
        if (data.estimatedFlightTime() <= 1) {
            throw new ValidatorException("tempo_voo_estimado", "O avião está parado ou voo inexistente");
        }
        if (data.estimatedFlightTime() > 420) {
            throw new ValidatorException("tempo_voo_estimado", "O tempo de voo não pode exceder 24 horas");
        }
    }
}
