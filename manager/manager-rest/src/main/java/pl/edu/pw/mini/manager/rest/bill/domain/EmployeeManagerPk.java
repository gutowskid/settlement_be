package pl.edu.pw.mini.manager.rest.bill.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeManagerPk implements Serializable {
    private String employeeId;
    private String managerId;
}
