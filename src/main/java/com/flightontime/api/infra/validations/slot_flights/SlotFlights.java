package com.flightontime.api.infra.validations.slot_flights;

import com.flightontime.api.dto.RequestDTO;
import com.flightontime.api.infra.ValidatorException;
import com.flightontime.api.infra.validations.RepositoryValidator;
import org.springframework.stereotype.Component;

@Component
public class SlotFlights implements RepositoryValidator {
    @Override
    public void validator(RequestDTO data) {
        if (data.voosNoSlot() == null) {
            throw new ValidatorException("voos_no_slot", "Voos no slot não pode ser nulo");
        }
        if (data.voosNoSlot() < 0) {
            throw new ValidatorException("voos_no_slot", "A quantidade de voos no slot não pode ser negativa");
        }
    }
}
