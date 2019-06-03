package pl.edu.pw.mini.core.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import org.apache.http.conn.HttpClientConnectionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.pw.mini.core.invoker.rest.Rest;
import pl.edu.pw.mini.core.security.authentication.TokenHandler;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractTest {

    @LocalServerPort
    private int port;

    @Value("${server.servlet.context-path}")
    private String path;

    @Rest
    @Autowired
    private HttpClientConnectionManager restClientConnectionManager;

    @Autowired
    protected TokenHandler tokenHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUpRestAssured() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (type, s) -> objectMapper
        ));
        RestAssured.port = this.port;
        RestAssured.basePath = this.path;
    }

    @After
    @Before
    public void closeConnections() {
        restClientConnectionManager.closeExpiredConnections();
        restClientConnectionManager.closeIdleConnections(0, TimeUnit.MILLISECONDS);
    }
}
