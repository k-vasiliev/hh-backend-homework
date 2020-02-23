package ru.hh.backend.resource;

import org.junit.Test;
import ru.hh.backend.config.TestBase;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResumeResourceWebTest extends TestBase {

    private static final String URL = "/api/resume";

    @Test
    public void shouldGetRequestOk() {
        assertThat(target(URL).request().get().getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }
}
