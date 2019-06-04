package pl.edu.pw.mini.employee.rest.bill;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tests.AbstractTest;
import pl.edu.pw.mini.employee.api.bill.CreateBillDto;
import pl.edu.pw.mini.employee.rest.bill.domain.Bill;
import pl.edu.pw.mini.employee.rest.bill.domain.BillRepository;
import pl.edu.pw.mini.model.UserRole;
import pl.edu.pw.mini.model.bill.BillStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static pl.edu.pw.mini.employee.rest.common.ErrorCode.*;

public class BillTest extends AbstractTest {

    @Autowired
    private BillRepository billRepository;

    @Test
    public void generateBillTest() {
        LocalDateTime startDate = LocalDateTime.now();
        String settlementNumber = "NUMBER1";
        String employeeId = "employee1";
        LocalDate from = LocalDate.of(2019, 5, 1);
        LocalDate to = LocalDate.of(2019, 5, 3);
        CreateBillDto createBillDto = CreateBillDto.builder().settlementNumber(settlementNumber).from(from).to(to).build();

        Integer id = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken(employeeId, UserRole.EMPLOYEE))
                .body(createBillDto)
        .when()
                .log().all()
                .post("/bill/generate")
        .then()
                .log().all()
                .statusCode(200)
                .body("employeeId", equalTo(employeeId))
                .body("brutto", equalTo(320))
                .body("netto", equalTo(273.92f))
                .body("incomeCosts", equalTo(64.0f))
                .body("tax", equalTo(46.08f))
                .body("hours", equalTo(16))
                .body("salary", equalTo(20))
                .body("settlementNumber", equalTo(settlementNumber))
                .body("from", equalTo(1556661600000L))
                .body("to", equalTo(1556834400000L))
                .body("status", equalTo(BillStatus.SAVED.name()))
                .extract().path("id");

        LocalDateTime endDate = LocalDateTime.now();

        Optional<Bill> bill = billRepository.findById(id.longValue());
        Assert.assertTrue(bill.isPresent());
        Assert.assertThat(bill.get().getCreationDate(), greaterThan(startDate));
        Assert.assertThat(bill.get().getCreationDate(), lessThan(endDate));
        Assert.assertThat(bill.get().getUpdateDate(), greaterThan(startDate));
        Assert.assertThat(bill.get().getUpdateDate(), lessThan(endDate));
        Assert.assertNull(bill.get().getAcceptorId());
        Assert.assertNull(bill.get().getPayerId());
        Assert.assertFalse(bill.get().getProcessed());
    }

    @Test
    public void generateBillExpectedFailNoAccountNumberTest() {
        String employeeId = "employee2";
        LocalDate from = LocalDate.of(2019, 5, 1);
        LocalDate to = LocalDate.of(2019, 5, 3);
        CreateBillDto createBillDto = CreateBillDto.builder().settlementNumber("NUMBER2").from(from).to(to).build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken(employeeId, UserRole.EMPLOYEE))
                .body(createBillDto)
        .when()
                .log().all()
                .post("/bill/generate")
        .then()
                .log().all()
                .statusCode(400)
                .body("errorCode", equalTo(EMP_0002.getCode()))
                .body("message", equalTo(EMP_0002.getMessage()));
    }

    @Test
    public void generateBillExpectedFailNoSalaryTest() {
        String employeeId = "employee4";
        LocalDate from = LocalDate.of(2019, 5, 1);
        LocalDate to = LocalDate.of(2019, 5, 3);
        CreateBillDto createBillDto = CreateBillDto.builder().settlementNumber("NUMBER3").from(from).to(to).build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken(employeeId, UserRole.EMPLOYEE))
                .body(createBillDto)
                .when()
                .log().all()
                .post("/bill/generate")
                .then()
                .log().all()
                .statusCode(400)
                .body("errorCode", equalTo(EMP_0001.getCode()))
                .body("message", equalTo(EMP_0001.getMessage()));
    }

    @Test
    public void generateBillExpectedFailBillAlreadyExistsTest() {
        String employeeId = "employee5";
        LocalDate from = LocalDate.of(2019, 5, 1);
        LocalDate to = LocalDate.of(2019, 5, 3);
        CreateBillDto createBillDto = CreateBillDto.builder().settlementNumber("NUMBER4").from(from).to(to).build();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(Constants.TOKEN, tokenHandler.getTestToken(employeeId, UserRole.EMPLOYEE))
                .body(createBillDto)
                .when()
                .log().all()
                .post("/bill/generate")
                .then()
                .log().all()
                .statusCode(400)
                .body("errorCode", equalTo(EMP_0007.getCode()))
                .body("message", equalTo(EMP_0007.getMessage()));
    }
}
