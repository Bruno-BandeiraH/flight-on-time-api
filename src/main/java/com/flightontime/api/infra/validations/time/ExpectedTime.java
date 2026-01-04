package com.flightontime.api.infra.validations.time;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExpectedTime implements RepositoryValidator {
    @Override
    public void validator(RequestDTO data) {
        if (data.horaPrevista() == null) {
            throw new ValidatorException("hora_prevista", "Horario é obrigatório");
        }
        if (data.horaPrevista().isAfter(LocalDateTime.now().plusYears(1))) {
            throw new ValidatorException("hora_prevista", "A data não pode ser superior a 1 ano à frente");
        }
    }
}
