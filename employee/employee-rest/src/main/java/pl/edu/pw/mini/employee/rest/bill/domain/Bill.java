package pl.edu.pw.mini.employee.rest.bill.domain;

import pl.edu.pw.mini.core.model.bill.BaseBill;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BILL")
public class Bill extends BaseBill {
}
