package pl.edu.pw.mini.employee.rest.accountnumber.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT_NUMBERS")
public class AccountNumber {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
}
