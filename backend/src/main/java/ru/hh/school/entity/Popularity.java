package ru.hh.school.entity;

public class Popularity {

    private static final int BORDER_POPULARITY = 50;

    public Popularity() {
    }

    public enum popularity {
        POPULAR, REGULAR
    }

    public String getPopularityValue(Integer viewsCount) {
       if (viewsCount == null) {
           return popularity.REGULAR.toString();
        }

       return viewsCount > BORDER_POPULARITY ? popularity.POPULAR.toString() : popularity.REGULAR.toString();
    }

}
