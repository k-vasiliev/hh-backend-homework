package ru.hh.school.util;

import org.jvnet.hk2.annotations.Service;

@Service
public class StringParameterFilter {
    public String filter(String query) {
        return query == null ? "" : query;
    }
}
