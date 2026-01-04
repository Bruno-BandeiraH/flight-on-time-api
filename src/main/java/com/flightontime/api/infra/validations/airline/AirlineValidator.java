package com.flightontime.api.infra.validations.airline;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class AirlineValidator implements RepositoryValidator {
    @Override
    public void validator(RequestDTO data) {
        if (data.empresaAerea() == null || data.empresaAerea().isBlank()) {
            throw new ValidatorException("icao_empresa_aerea", "Empresa aérea é obrigatória");
        }
        if (!data.empresaAerea().equals(data.empresaAerea().toUpperCase()) || !data.empresaAerea().matches("[A-Z]+")) {
            throw new ValidatorException("icao_empresa_aerea", "Deve conter apenas letras maiúsculas");
        }
        if (data.empresaAerea().length() != 2) {
            throw new ValidatorException("icao_empresa_aerea", "O campo deve ter 2 caracteres");
        }
        if (!data.empresaAerea().matches("[a-zA-Z]+")) {
            throw new ValidatorException("icao_empresa_aerea", "O campo deve conter apenas letras");
        }
    }

}
