package pl.edu.pw.mini.payroll.rest.bill.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.core.model.bill.BaseBill;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "BILL")
public class Bill extends BaseBill {
}
