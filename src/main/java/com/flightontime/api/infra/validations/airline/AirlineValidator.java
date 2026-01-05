package com.flightontime.api.infra.validations.airline;

import com.flightontime.api.dto.PredictionRequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class AirlineValidator implements RepositoryValidator {
    @Override
    public void validator(PredictionRequestDTO data) {
        if (data.icaoAirline() == null || data.icaoAirline().isBlank()) {
            throw new ValidatorException("icao_empresa_aerea", "Empresa aérea é obrigatória");
        }
        if (!data.icaoAirline().equals(data.icaoAirline().toUpperCase()) || !data.icaoAirline().matches("[A-Z]+")) {
            throw new ValidatorException("icao_empresa_aerea", "Deve conter apenas letras maiúsculas");
        }
        if (data.icaoAirline().length() != 2) {
            throw new ValidatorException("icao_empresa_aerea", "O campo deve ter 2 caracteres");
        }
        if (!data.icaoAirline().matches("[a-zA-Z]+")) {
            throw new ValidatorException("icao_empresa_aerea", "O campo deve conter apenas letras");
        }
    }

}
