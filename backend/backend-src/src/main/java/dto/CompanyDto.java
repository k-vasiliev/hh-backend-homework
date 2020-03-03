package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.CompanyEntity;
import entity.UsersEntity;

public class CompanyDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    public CompanyDto(CompanyEntity entity) {
        this.name = entity.getCompanyName();
        this.id = entity.getCompanyId();
    }
}
