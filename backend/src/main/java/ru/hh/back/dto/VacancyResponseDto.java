package ru.hh.back.dto;

public class VacancyResponseDto {
    private String title;
    private Company company;
    private Integer salary;
    private String description;
    private String contacts;
    private Integer id;
    private String dateCreate;

    public String getTitle() {
        return title;
    }
    public VacancyResponseDto(){

    }

    public VacancyResponseDto(Integer id, String title, String companyName, Integer salary, String description, String contacts, String dateCreate) {
        this.id = id;
        this.title = title;
        this.company = new Company(companyName);
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
        this.dateCreate = dateCreate;
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

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
