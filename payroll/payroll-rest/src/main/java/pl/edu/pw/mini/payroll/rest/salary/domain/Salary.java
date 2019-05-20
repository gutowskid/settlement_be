package pl.edu.pw.mini.payroll.rest.salary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.core.model.salary.BaseSalary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SALARY")
public class Salary extends BaseSalary {
}
