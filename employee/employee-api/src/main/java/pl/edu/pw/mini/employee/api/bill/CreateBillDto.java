package pl.edu.pw.mini.employee.api.bill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBillDto {
    private String settlementNumber;
    private LocalDate from;
    private LocalDate to;
}
