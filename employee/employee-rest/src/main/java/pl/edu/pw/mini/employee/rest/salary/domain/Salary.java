package pl.edu.pw.mini.employee.rest.salary.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.core.model.salary.BaseSalary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SALARY")
public class Salary extends BaseSalary {
}
