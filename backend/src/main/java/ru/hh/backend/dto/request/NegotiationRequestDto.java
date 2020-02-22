package ru.hh.backend.dto.request;

import lombok.Data;

@Data
public class NegotiationRequestDto {

    private Long resumeId;

    private Long vacancyId;
}
