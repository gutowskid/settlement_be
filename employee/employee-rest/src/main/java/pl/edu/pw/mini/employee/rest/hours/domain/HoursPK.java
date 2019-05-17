package pl.edu.pw.mini.employee.rest.hours.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoursPK implements Serializable {
    private String employeeId;
    private LocalDate day;
}
