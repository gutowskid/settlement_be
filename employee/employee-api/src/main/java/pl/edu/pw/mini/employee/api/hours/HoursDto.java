package pl.edu.pw.mini.employee.api.hours;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoursDto {
    private LocalDate day;
    private String task;
    private Long count;
}
