package com.flightontime.api.infra.validations.destination;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class DestinationValidator implements RepositoryValidator {
    @Override
    public void validator(RequestDTO data) {
        if (data.icaoDestino() == null || data.icaoDestino().isBlank()) {
            throw new ValidatorException("icao_aerodromo_destino", "Destino é obrigatório");
        }
        if (!data.icaoDestino().equals(data.icaoDestino().toUpperCase()) || !data.icaoDestino().matches("[A-Z]+")) {
            throw new ValidatorException("icao_aerodromo_destino", "Deve conter apenas letras maiúsculas");
        }
        if (data.icaoDestino().length() != 4) {
            throw new ValidatorException("icao_aedromo_destino", "O campo deve ter 2 caracteres");
        }
        if (!data.icaoDestino().matches("[a-zA-Z]+")) {
            throw new ValidatorException("icao_aedromo_destino", "O campo deve conter apenas letras");
        }
    }
}
