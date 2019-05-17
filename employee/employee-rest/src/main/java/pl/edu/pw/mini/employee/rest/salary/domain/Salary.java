package pl.edu.pw.mini.employee.rest.salary.domain;

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
@Table(name = "SALARY")
public class Salary {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "SALARY")
    private Long salary;
}
