package ru.hh.back.resource;

        import javax.ws.rs.DefaultValue;
        import javax.ws.rs.GET;
        import javax.ws.rs.Path;
        import javax.ws.rs.QueryParam;

@Path("/api/company")
public class CompanyResource {

    @GET
    @Path("/hello")
    public String hello(@DefaultValue("world") @QueryParam("name") String name) {
        return String.format("Hello, %s!", name);
    }
}


