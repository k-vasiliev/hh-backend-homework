package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;

@Service
public class QueryFilter {
    public String filter(String query) {
        return query == null ? "?text=" : "?text=" + query;
    }
}
