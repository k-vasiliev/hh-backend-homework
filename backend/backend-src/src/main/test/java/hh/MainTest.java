package hh;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.LocalApplicationConfig;
import dto.NewResumeDto;
import dto.ResumeDto;
import dto.UserDto;
import org.eclipse.jetty.server.Server;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import javax.crypto.spec.PSource;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class MainTest
{

    EmbeddedPostgres startPostgres() throws IOException {
        EmbeddedPostgres postgres = new EmbeddedPostgres();
        final String url = postgres.start("localhost", 5432, "hh", "hh", "hh");
        return postgres;
    }

    UserDto [] getUsers(Client client) {
        WebTarget webTarget = client.target("http://localhost:8080/api/user/");
        return  webTarget
                .queryParam("type", "applicant")
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .get(UserDto[].class);
    }


    void userApiMakeRequests(Client client) {
        assertEquals(0, getUsers(client).length);

        hh.TestNewUserDto newUser = new hh.TestNewUserDto("vasya", "APPLICANT");

        WebTarget webTarget = client.target("http://localhost:8080/api/user/");
        Response vasyaCreateResponse = webTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newUser, MediaType.APPLICATION_JSON));

        Integer vasyaId = vasyaCreateResponse.readEntity(Integer.class);
        System.out.println("New user Vasya, id = " +vasyaId);

        newUser = new hh.TestNewUserDto("petya", "COMPANY");
        Response petyaResponse = webTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newUser, MediaType.APPLICATION_JSON));

        Integer petyaId = petyaResponse.readEntity(Integer.class);
        System.out.println("New company user Petya, id = "+petyaId);


        Response usersResponse = webTarget
                .queryParam("type", "applicant")
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        UserDto [] items  = usersResponse.readEntity( UserDto[].class );

        assertEquals(items.length, 1);
        assertTrue(items[0].equals(new UserDto("vasya", vasyaId)));

    }



    @Test
    public void userApiTest() throws Exception {
        EmbeddedPostgres postgres = startPostgres();
        // Spring
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(LocalApplicationConfig.class);
        applicationContext.scan("dao", "controller", "service");
        applicationContext.refresh();

        // Init DB
        SessionFactory sessionFactory = applicationContext.getBean("sessionFactory", SessionFactory.class);
        hh.TestHelper.initDb(sessionFactory);

        //Start Server
        Server srv = Main.createHHServer(applicationContext, 8080);
        srv.start();

        Client client = ClientBuilder.newClient();

        // Do requests
        try {
            userApiMakeRequests(client);
        } finally {
            // Stop server
            client.close();
            srv.stop();
            srv.join();
            postgres.stop();
        }
    }

    void resumeApiMakeRequests(Client client) {
        WebTarget webTarget = client.target("http://localhost:8080/api/resume/");

        ResumeDto[] emptyResponse = webTarget
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .get(ResumeDto[].class);

        System.out.println("Empty UserResume list = " + emptyResponse);
        assertEquals(0, emptyResponse.length);

        // Get users
        UserDto [] users  = getUsers(client);
        System.out.println("Users = " + users + " Length = " + users.length);

        Integer maxUid = Arrays.asList(users)
                .stream()
                .max( (UserDto a, UserDto b)-> a.getId() > b.getId()? 1 : -1)
                .get().getId();

        NewResumeDto newResume;
        Response resumeCreateResponse;

        // Try resume with incorrect UID
        System.out.println("Creating new 'incorrect' resume");
        newResume = new NewResumeDto("No contacts","New resume", maxUid + 1, "No Experience");
        resumeCreateResponse= webTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newResume, MediaType.APPLICATION_JSON));
        System.out.println(resumeCreateResponse);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), resumeCreateResponse.getStatus());

        // Create resume with correct UID
        System.out.println("Creating correct resume");
        newResume = new NewResumeDto("No contacts","New resume", maxUid, "No Experience");
        resumeCreateResponse = webTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newResume, MediaType.APPLICATION_JSON));
        System.out.println(resumeCreateResponse);
        assertEquals(resumeCreateResponse.getStatus(), Response.Status.OK.getStatusCode());

        // Try duplicate UID/.../.../
        //System.out.println("Creating duplicate resume");
        //resumeCreateResponse = webTarget
        //        .request(MediaType.APPLICATION_JSON)
        //        .post(Entity.entity(newResume, MediaType.APPLICATION_JSON));
        //System.out.println(resumeCreateResponse);

        System.out.println("Reading all resume");
        ResumeDto[] resumeList = webTarget
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
                .get(ResumeDto[].class);

        System.out.println("ResumeList = " + resumeList);
    }


    @Test
    public void resumeTest() throws Exception {
        EmbeddedPostgres postgres = startPostgres();
        // Spring
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(LocalApplicationConfig.class);
        applicationContext.scan("dao", "controller", "service");
        applicationContext.refresh();

        // Init DB
        SessionFactory sessionFactory = applicationContext.getBean("sessionFactory", SessionFactory.class);
        hh.TestHelper.initDb(sessionFactory);

        //Start Server
        Server srv = Main.createHHServer(applicationContext, 8080);
        srv.start();

        Client client = ClientBuilder.newClient();

        // Do requests
        try {
            userApiMakeRequests(client);
            resumeApiMakeRequests(client);
        } finally {
            // Stop server
            client.close();
            srv.stop();
            srv.join();
            postgres.stop();
        }

    }

    @Test
    public void jsonTest() throws IOException {
        String responseArray = "[{\"name\":\"vasya\",\"id\":1}]";
        ObjectMapper mapper = new ObjectMapper();
        UserDto[] users = mapper.readValue(responseArray.getBytes(), UserDto[].class);
        System.out.println(users);
    }


}


