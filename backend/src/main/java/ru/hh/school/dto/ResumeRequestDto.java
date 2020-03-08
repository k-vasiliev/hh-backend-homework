package ru.hh.school.dto;

import ru.hh.school.entity.UserEntity;

public class ResumeRequestDto {

    private String resumeName;
    private String header;
    private String experience;
    private String contacts;
    private UserEntity userEntity;

    public ResumeRequestDto(String resumeName, String header, String experience, String contacts, UserEntity userEntity) {
        this.resumeName = resumeName;
        this.header = header;
        this.userEntity = userEntity;
        this.experience = experience;
        this.contacts = contacts;
    }

    public String getResumeName() {
        return resumeName;
    }

    public String getHeader() {
        return header;
    }

    public String getExperience() {
        return experience;
    }

    public String getContacts() {
        return contacts;
    }



    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
