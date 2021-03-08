package ru.hh.school.exception;

public class ExceptionResponse {

    private String description;
    private Integer statusCode;

    public ExceptionResponse() {}

    public ExceptionResponse(String description, Integer statusCode) {
        this.description = description;
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
