package ru.hh.school;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Utils {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.MEDIUM)
        .withLocale(Locale.forLanguageTag("RU"))
        .withZone(ZoneId.of("Europe/Moscow"));

    public static void check(Boolean condition) {
        if (!condition) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
