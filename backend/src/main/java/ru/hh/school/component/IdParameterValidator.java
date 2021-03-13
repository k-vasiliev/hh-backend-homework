package ru.hh.school.component;

import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.BadRequestException;

@Service
public class IdParameterValidator {
    public Integer validate(Integer id) throws BadRequestException {
            if (id < 0) {
                throw new BadRequestException("id parameter can't be negative");
            } return id;
    }
}
