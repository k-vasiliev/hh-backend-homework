package ru.hh.nab.util;

import ru.hh.nab.entity.UserType;

public class Utils {
    public static UserType setType(String type) {
        return "employer".equals(type.toLowerCase()) ? UserType.EMPLOYER : UserType.APPLICANT;
    }
}
