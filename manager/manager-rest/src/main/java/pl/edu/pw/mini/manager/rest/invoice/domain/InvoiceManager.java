package pl.edu.pw.mini.manager.rest.invoice.domain;

import lombok.Data;
import pl.edu.pw.mini.core.model.invoice.BaseInvoice;
import pl.edu.pw.mini.manager.rest.employeemanager.domain.EmployeeManager;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "INVOICE")
public class InvoiceManager extends BaseInvoice {

    @OneToMany
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private List<EmployeeManager> managers;
}
