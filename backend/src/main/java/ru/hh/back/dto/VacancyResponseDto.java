package ru.hh.back.dto;

public class VacancyResponseDto {
    private String title;
    private Company company;
    private Integer salary;
    private String description;
    private String contacts;
    private Integer vacancyId;

    public String getTitle() {
        return title;
    }
    public VacancyResponseDto(){

    }

    public VacancyResponseDto(Integer vacancyId, String title, String companyName, Integer salary, String description, String contacts) {
        this.vacancyId = vacancyId;
        this.title = title;
        this.company = new Company(companyName);
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
    }

    public class Company {
        private String name;
        public Company(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }
}
