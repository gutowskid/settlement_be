package pl.edu.pw.mini.core.tests;

import org.apache.http.conn.HttpClientConnectionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.pw.mini.core.invoker.rest.Rest;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractTest {

    //@LocalServerPort
    private int port;

    @Rest
    @Autowired
    private HttpClientConnectionManager restClientConnectionManager;

    @Before
    @After
    public void closeConnections() {
        restClientConnectionManager.closeExpiredConnections();
        restClientConnectionManager.closeIdleConnections(0, TimeUnit.MILLISECONDS);
    }
}
