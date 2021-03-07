package ru.hh.school.exception;

public class InvalidPaginationException extends ApiRequestException {
    public InvalidPaginationException(String message) {
        super(message);
    }
}
