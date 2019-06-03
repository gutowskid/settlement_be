package pl.edu.pw.mini.employee.rest.accountnumber;

import io.restassured.http.ContentType;
import org.junit.Test;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tests.AbstractTest;
import pl.edu.pw.mini.core.tools.FluentMapBuilder;
import pl.edu.pw.mini.model.UserRole;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static pl.edu.pw.mini.employee.rest.common.ErrorCode.*;

public class AccountNumberTest extends AbstractTest {

    @Test
    public void getMyAccountNumberTest() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken("employee1", UserRole.EMPLOYEE))
        .when()
                .log().all()
                .get("/accountnumber")
        .then()
                .log().all()
                .statusCode(200)
                .body("value", equalTo("61109010140000071219812874"));
    }

    @Test
    public void getMyAccountNumberExpectedFailTest() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken("employee2", UserRole.EMPLOYEE))
        .when()
                .log().all()
                .get("/accountnumber")
        .then()
                .log().all()
                .statusCode(400)
                .body("errorCode", equalTo(EMP_0002.getCode()))
                .body("message", equalTo(EMP_0002.getMessage()));
    }

    @Test
    public void setMyAccountNumberTest() {
        Map<String, String> params = FluentMapBuilder.<String, String>aMap().with("number", "61109010140000071219812874").build();
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken("employee3", UserRole.EMPLOYEE))
        .when()
                .log().all()
                .post("/accountnumber?number={number}", params)
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void setMyAccountExpectedFailNumberTest() {
        Map<String, String> params = FluentMapBuilder.<String, String>aMap().with("number", "71109010140000071219812874").build();
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken("employee4", UserRole.EMPLOYEE))
        .when()
                .log().all()
                .post("/accountnumber?number={number}", params)
        .then()
                .log().all()
                .statusCode(400)
                .body("errorCode", equalTo(EMP_0003.getCode()))
                .body("message", equalTo(EMP_0003.getMessage()));
    }
}
