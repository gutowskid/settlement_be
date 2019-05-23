package pl.edu.pw.mini.model.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Long id;
    private String employeeId;
    private Long amount;
    private InvoiceStatus status;
    private String settlementNumber;
}
