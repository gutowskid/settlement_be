package pl.edu.pw.mini.manager.rest.bill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EmployeeManagerPk.class)
@Table(name = "EMPLOYEE_MANAGER")
public class EmployeeManager {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Id
    @Column(name = "MANAGER_ID")
    private String managerId;
}
