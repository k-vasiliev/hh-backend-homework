package ru.hh.backend.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String userType;

    private String dateCreate;
}
