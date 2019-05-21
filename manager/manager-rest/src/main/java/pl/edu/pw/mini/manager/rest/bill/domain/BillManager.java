package pl.edu.pw.mini.manager.rest.bill.domain;

import lombok.Data;
import pl.edu.pw.mini.core.model.bill.BaseBill;
import pl.edu.pw.mini.manager.rest.employeemanager.domain.EmployeeManager;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "BILL")
public class BillManager extends BaseBill implements Serializable {

    @OneToMany
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private List<EmployeeManager> managers;
}
