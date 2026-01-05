package com.flightontime.api.infra.validations.origin;

import com.flightontime.api.dto.PredictionRequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class OriginValidator implements RepositoryValidator {
    @Override
    public void validator(PredictionRequestDTO data) {
        if (data.icaoOrigin() == null || data.icaoOrigin().isBlank()) {
            throw new ValidatorException("icao_aerodromo_origem", "Origem é obrigatória");
        }
        if (!data.icaoOrigin().equals(data.icaoOrigin().toUpperCase()) || !data.icaoOrigin().matches("[A-Z]+")) {
            throw new ValidatorException("icao_aerodromo_origem", "Deve conter apenas letras maiúsculas");
        }
        if (data.icaoOrigin().length() != 4) {
            throw new ValidatorException("icao_aerodromo_origem", "O campo deve ter 2 caracteres");
        }
        if (!data.icaoOrigin().matches("[a-zA-Z]+")) {
            throw new ValidatorException("icao_aerodromo_origem", "O campo deve conter apenas letras");
        }
    }
}
