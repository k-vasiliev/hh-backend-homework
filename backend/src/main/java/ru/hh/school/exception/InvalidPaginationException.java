package ru.hh.school.exception;

public class InvalidPaginationException extends Exception {
    public InvalidPaginationException(String error) {
        super(error);
    }
}
