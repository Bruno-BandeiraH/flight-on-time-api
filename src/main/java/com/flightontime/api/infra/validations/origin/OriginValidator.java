package com.flightontime.api.infra.validations.origin;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class OriginValidator implements RepositoryValidator {
    @Override
    public void validator(RequestDTO data) {
        if (data.icaoOrigem() == null || data.icaoOrigem().isBlank()) {
            throw new ValidatorException("icao_aerodromo_origem", "Origem é obrigatória");
        }
        if (!data.icaoOrigem().equals(data.icaoOrigem().toUpperCase()) || !data.icaoOrigem().matches("[A-Z]+")) {
            throw new ValidatorException("icao_aerodromo_origem", "Deve conter apenas letras maiúsculas");
        }
        if (data.icaoOrigem().length() != 4) {
            throw new ValidatorException("icao_aerodromo_origem", "O campo deve ter 2 caracteres");
        }
        if (!data.icaoOrigem().matches("[a-zA-Z]+")) {
            throw new ValidatorException("icao_aerodromo_origem", "O campo deve conter apenas letras");
        }
    }
}
