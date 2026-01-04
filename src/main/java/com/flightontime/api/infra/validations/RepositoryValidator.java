package com.flightontime.api.infra.validations;


import com.flightontime.api.dto.RequestDTO;

public interface RepositoryValidator {
    void validator(RequestDTO data);

}
