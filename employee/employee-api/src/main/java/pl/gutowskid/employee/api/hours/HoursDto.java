package pl.gutowskid.employee.api.hours;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoursDto {
    private LocalDate day;
    private String task;
    private Long count;
}
