package pl.edu.pw.mini.employee.rest.salary;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tests.AbstractTest;
import pl.edu.pw.mini.model.UserRole;

import static io.restassured.RestAssured.given;

public class SalaryTest extends AbstractTest {

    @Test
    public void getMySalaryTest() {
        Long salary = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken("employee1", UserRole.EMPLOYEE))
        .when()
                .log().all()
                .get("/salary")
        .then()
                .log().all()
                .statusCode(200)
                .extract().as(Long.class);

        Assert.assertEquals(20L, salary.longValue());
    }
}
