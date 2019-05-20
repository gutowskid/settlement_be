package pl.edu.pw.mini.model.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private String employeeId;
    private Long salary;
}
