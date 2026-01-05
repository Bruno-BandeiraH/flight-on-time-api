package com.flightontime.api.infra.validations.slot_flights;

import com.flightontime.api.dto.PredictionRequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class SlotFlights implements RepositoryValidator {
    @Override
    public void validator(PredictionRequestDTO data) {
        if (data.slotFlights() == null) {
            throw new ValidatorException("voos_no_slot", "Voos no slot não pode ser nulo");
        }
        if (data.slotFlights() < 0) {
            throw new ValidatorException("voos_no_slot", "A quantidade de voos no slot não pode ser negativa");
        }
    }
}
