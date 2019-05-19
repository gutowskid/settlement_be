package pl.edu.pw.mini.manager.rest.bill.domain;

import pl.edu.pw.mini.core.model.bill.BaseBill;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BILL")
public class BillManager extends BaseBill implements Serializable {

    @OneToMany
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private List<EmployeeManager> managers;
}
