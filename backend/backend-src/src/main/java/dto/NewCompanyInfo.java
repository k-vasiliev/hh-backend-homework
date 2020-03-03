package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCompanyInfo {
    @JsonProperty("name")
    private String name;

    @JsonProperty("userId")
    private Integer userId;

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }
}

