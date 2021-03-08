package ru.hh.school.util;

import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.BadRequestException;

@Service
public class PaginationValidator {

    public Boolean validate(Integer page, Integer perPage) throws BadRequestException {
        return (validPage(page) && validPerPage(perPage));
    }

    private Boolean validPage(Integer page) throws BadRequestException {
            if (page >= 0) {
                return true;
            }
            throw new BadRequestException("page parameter can't be negative");
    }

    private Boolean validPerPage(Integer perPage) throws BadRequestException {
            if (perPage < 0) {
                throw new BadRequestException("per_page parameter can't be negative");
            }
            if (perPage > 100) {
                throw new BadRequestException("per_page parameter can't be greater then 100");
            }
            return true;
        }
}
