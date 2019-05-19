package pl.edu.pw.mini.employee.rest.bill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.employee.api.bill.BillStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BILL")
public class Bill {

    @Id //TODO
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "HOURS")
    private Long hours;

    @Column(name = "SALARY")
    private Long salary;

    @Column(name = "BRUTTO")
    private Long brutto;

    @Column(name = "NETTO")
    private Double netto;

    @Column(name = "INCOME_COSTS")
    private Double incomeCosts;

    @Column(name = "TAX")
    private Double tax;

    @Column(name = "CREATION_DATA")
    private LocalDateTime creationDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "STATUS")
    private BillStatus status;

    @Column(name = "SETTLEMENT_NUMBER")
    private String settlementNumber;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCEPTOR_ID")
    private String acceptorId;

    @Column(name = "PAYER_ID")
    private String payerId;

    @Column(name = "PROCESSED")
    private Boolean processed;

    @Column(name = "FROM_DATE")
    private LocalDate fromDate;

    @Column(name = "TO_DATE")
    private LocalDate toDate;

}
