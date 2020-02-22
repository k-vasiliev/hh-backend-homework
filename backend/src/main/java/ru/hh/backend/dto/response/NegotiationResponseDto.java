package ru.hh.backend.dto.response;

import lombok.Data;

@Data
public class NegotiationResponseDto {

    private Long id;

    private Resume resume;

    @Data
    public static class Resume {

        private String title;

        private Applicant applicant;

        @Data
        public static class Applicant {

            private String name;
        }
    }
}
