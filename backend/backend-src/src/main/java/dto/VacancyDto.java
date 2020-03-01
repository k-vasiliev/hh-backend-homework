package dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.VacancyEntity;

public class VacancyDto {
    @JsonProperty("id")
    private Integer     id;

    @JsonProperty("title")
    private String      vacancyTitle;

    @JsonProperty("company")
    private ApplicantDto company;

    @JsonProperty("dateCreate")
    private String      dateCreate;

    @JsonProperty("salary")
    private Integer     salary;

    @JsonProperty("description")
    private String      description;

    @JsonProperty("contacts")
    private String      contacts;

    public VacancyDto(VacancyEntity vacancyEntity) {
        this.vacancyTitle = vacancyEntity.getTitle();
        this.company = new ApplicantDto(vacancyEntity.getCompany().getCompanyName());
        this.dateCreate = vacancyEntity.getCreated().toString();
        this.salary = vacancyEntity.getSalary();
        this.description = vacancyEntity.getDescription();
        this.contacts = vacancyEntity.getContacts();
        this.id = vacancyEntity.getId();
    }

}
