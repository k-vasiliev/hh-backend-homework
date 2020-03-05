package ru.hh.back.entity;

public enum UserType {

    APPLICANT("APPLICANT"),
    EMPLOYER("EMPLOYER");


    private final String name;

    private UserType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {

        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
