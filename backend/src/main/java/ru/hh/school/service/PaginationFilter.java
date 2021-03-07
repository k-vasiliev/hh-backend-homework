package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.exception.InvalidPaginationException;

@Service
public class PaginationFilter {

    public String filter(String page, String perPage) throws InvalidPaginationException {
        String pageForQuery = page == null ? "&page=0" : validatePage(page);
        String perPageForQuery = perPage == null ? "&per_page=20" : validatePerPage(perPage);
        return pageForQuery + perPageForQuery;
    }

    private String validatePage(String page) throws InvalidPaginationException {
        try {
            int intPage = Integer.parseInt(page);
            if (intPage >= 0) {
                return "&page=" + intPage;
            }
            throw new InvalidPaginationException("page parameter can't be negative");
        } catch (NumberFormatException e) {
            throw new InvalidPaginationException("Unable to parse page parameter");
        }
    }

    private String validatePerPage(String perPage) throws InvalidPaginationException {
        try {
            int intPerPage = Integer.parseInt(perPage);
            if (intPerPage < 0) {
                throw new InvalidPaginationException("per_page parameter can't be negative");
            }
            if (intPerPage > 100) {
                throw new InvalidPaginationException("per_page parameter can't be greater then 100");
            }
            return "&per_page=" + intPerPage;
        } catch (NumberFormatException e) {
            throw new InvalidPaginationException("Unable to parse page parameter");
        }
    }

}
