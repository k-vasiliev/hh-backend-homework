package ru.hh.homework.at_least_some_backend;

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
}
