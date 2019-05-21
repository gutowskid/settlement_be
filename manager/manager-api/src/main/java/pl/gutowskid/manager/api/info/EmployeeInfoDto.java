package pl.gutowskid.manager.api.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfoDto {
    private String employeeId;
    private Long hours;
    private Long remuneration;
}
