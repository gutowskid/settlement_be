package pl.edu.pw.mini.employee.api.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.model.aws.FileDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddInvoiceDto {
    private Long amount;
    private String settlementNumber;
    private FileDto fileDto;
}
