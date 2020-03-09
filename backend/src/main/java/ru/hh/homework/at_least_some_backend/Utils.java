package ru.hh.homework.at_least_some_backend;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
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
}
