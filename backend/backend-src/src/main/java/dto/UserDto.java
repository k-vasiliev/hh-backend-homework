package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.UsersEntity;

public class UserDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    public UserDto(UsersEntity entity) {
        this.name = entity.getName();
        this.id = entity.getId();
    }
}
