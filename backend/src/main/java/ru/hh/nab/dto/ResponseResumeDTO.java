package ru.hh.nab.dto;

import java.time.LocalDate;

public class ResponseResumeDTO {

    private String heading;

    private String name;

    private LocalDate lastUpdate;

    private Integer resumeId;

    public ResponseResumeDTO(String heading, String name, LocalDate lastUpdate, Integer resumeId) {
        this.heading = heading;
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.resumeId = resumeId;
    }

    public String getHeading() {
        return heading;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }
}
