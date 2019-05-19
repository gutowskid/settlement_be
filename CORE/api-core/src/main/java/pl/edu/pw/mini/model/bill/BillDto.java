package pl.edu.pw.mini.model.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
    private Long id;
    private String employeeId;
    private Long brutto;
    private Double netto;
    private Double incomeCosts;
    private Double tax;
    private Long hours;
    private Long salary;
    private String settlementNumber;
    private LocalDate from;
    private LocalDate to;
}
