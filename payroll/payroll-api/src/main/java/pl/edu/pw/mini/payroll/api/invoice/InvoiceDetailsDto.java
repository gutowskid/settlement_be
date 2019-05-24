package pl.edu.pw.mini.payroll.api.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.model.invoice.InvoiceDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDto extends InvoiceDto {
    private String accountNumber;
}
