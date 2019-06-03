package pl.edu.pw.mini.employee.rest.hours;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tests.AbstractTest;
import pl.edu.pw.mini.employee.api.hours.HoursDto;
import pl.edu.pw.mini.employee.rest.hours.domain.Hours;
import pl.edu.pw.mini.employee.rest.hours.domain.HoursPK;
import pl.edu.pw.mini.employee.rest.hours.domain.HoursRepository;
import pl.edu.pw.mini.model.Period;
import pl.edu.pw.mini.model.UserRole;

import java.time.LocalDate;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HoursTest extends AbstractTest {

    @Autowired
    private HoursRepository repository;

    @Test
    public void getMyHoursTest() {
        Period period = Period.builder()
                .from(LocalDate.of(2019, 5, 1))
                .to(LocalDate.of(2019, 5, 3))
                .build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken("employee1", UserRole.EMPLOYEE))
                .body(period)
        .when()
                .log().all()
                .post("/hours/find")
        .then()
                .log().all()
                .statusCode(200)
                .body("[0].day", equalTo(1556661600000L))
                .body("[0].task", equalTo("TASK1"))
                .body("[0].count", equalTo(3))
                .body("[1].day", equalTo(1556748000000L))
                .body("[1].task", equalTo("TASK2"))
                .body("[1].count", equalTo(8))
                .body("[2].day", equalTo(1556834400000L))
                .body("[2].task", equalTo("TASK3"))
                .body("[2].count", equalTo(5));
    }

    @Test
    public void setMyHoursTest() {
        String employeeId = "employee2";
        LocalDate date = LocalDate.of(2019, 5, 10);
        String task = "TASK10";
        Long count = 7L;
        HoursDto dto = HoursDto.builder().day(date).task(task).count(count).build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken(employeeId, UserRole.EMPLOYEE))
                .body(dto)
        .when()
                .log().all()
                .post("/hours/report")
        .then()
                .log().all()
                .statusCode(200);

        Optional<Hours> hours = repository.findById(HoursPK.builder().employeeId(employeeId).day(date).build());
        Assert.assertTrue(hours.isPresent());
        Assert.assertEquals(count, hours.get().getCount());
        Assert.assertEquals(task, hours.get().getTask());
    }
}
