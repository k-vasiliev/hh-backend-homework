package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewUserDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    public boolean isCompany() {
        return type.equals("APPLICANT") ? false : true;
    }

    public String getName() {
        return name;
    }

    public NewUserDto(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public NewUserDto() {}




}
