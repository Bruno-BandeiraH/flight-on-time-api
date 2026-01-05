package com.flightontime.api.infra.validations.destination;

import com.flightontime.api.dto.PredictionRequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class DestinationValidator implements RepositoryValidator {
    @Override
    public void validator(PredictionRequestDTO data) {
        if (data.icaoDestination() == null || data.icaoDestination().isBlank()) {
            throw new ValidatorException("icao_aerodromo_destino", "Destino é obrigatório");
        }
        if (!data.icaoDestination().equals(data.icaoDestination().toUpperCase()) || !data.icaoDestination().matches("[A-Z]+")) {
            throw new ValidatorException("icao_aerodromo_destino", "Deve conter apenas letras maiúsculas");
        }
        if (data.icaoDestination().length() != 4) {
            throw new ValidatorException("icao_aerodromo_destino", "O campo deve ter 2 caracteres");
        }
        if (!data.icaoDestination().matches("[a-zA-Z]+")) {
            throw new ValidatorException("icao_aerodromo_destino", "O campo deve conter apenas letras");
        }
    }
}
