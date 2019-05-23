package pl.edu.pw.mini.core.model.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.model.invoice.InvoiceStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseInvoice {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @Column(name = "SETTLEMENT_NUMBER")
    private String settlementNumber;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCEPTOR_ID")
    private String acceptorId;

    @Column(name = "PAYER_ID")
    private String payerId;

    @Column(name = "ARCHIVED")
    private Boolean archived;

    @Column(name = "FILE_NAME")
    private String fileName;
}
