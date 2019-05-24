package pl.edu.pw.mini.payroll.api.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.model.bill.BillDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillAccountNumberDto extends BillDto {
    private String accountNumber;
}
