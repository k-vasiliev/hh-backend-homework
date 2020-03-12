package ru.hh.homework.at_least_some_backend;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils
{
    public static <T> T firstOrNull(List<T> list)
    {
        return list.size() > 0
                ? list.get(0)
                : null;
    }

    public static Response dtoToResponse(Object dto)
    {
        return dto != null
                ? Response.ok(dto).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    public static <E extends Enum<E>> E requireEnum(Class<E> eClass, String value, String paramName)
    {
        if (value == null)
            throw new BadRequestException(String.format("Missing parameter '%s'.", paramName));

        try
        {
            return Enum.valueOf(eClass, value.toUpperCase());
        }
        catch (IllegalArgumentException ex)
        {
            throw new BadRequestException(String.format("Invalid parameter '%s'.", paramName));
        }
    }

    // Почему-то во фронт попадает undefined, если я передаю объект а-ля DateTime, даже с @JsonFormat...
    public static ZonedDateTime toMoscowDateTime(OffsetDateTime dateTime)
    {
        if (dateTime == null) return null;
        return dateTime.atZoneSameInstant(ZoneId.of("Europe/Moscow"));
    }

    public static String formatDateTime(ZonedDateTime dateTime)
    {
        if (dateTime == null) return "<NULL>";
        return dateTime.format(
                DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
        );
    }
}
