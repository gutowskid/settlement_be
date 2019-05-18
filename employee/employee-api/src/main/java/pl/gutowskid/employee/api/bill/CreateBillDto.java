package pl.gutowskid.employee.api.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBillDto {
    private String settlementNumber;
    private LocalDate from;
    private LocalDate to;
}
