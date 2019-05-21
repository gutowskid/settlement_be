package pl.edu.pw.mini.manager.rest.employeemanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.core.model.user.Users;

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

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private Users user;
}
