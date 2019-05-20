package pl.edu.pw.mini.core.model.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseSalary {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "SALARY")
    private Long salary;

}
