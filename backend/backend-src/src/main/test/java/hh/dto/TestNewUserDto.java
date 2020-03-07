package hh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestNewUserDto {
    @JsonProperty("name")
    String name;


    @JsonProperty("type")
    String type;


    public TestNewUserDto(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
