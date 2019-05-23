package pl.edu.pw.mini.core.model.invoice;

import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.model.invoice.InvoiceDto;

public abstract class BaseInvoiceDtoAssembler<X extends BaseInvoice, Z extends InvoiceDto> extends DtoAssembler<X, Z> {

    public void fill(X input, Z dto) {
        dto.setId(input.getId());
        dto.setEmployeeId(input.getEmployeeId());
        dto.setAmount(input.getAmount());
        dto.setStatus(input.getStatus());
        dto.setSettlementNumber(input.getSettlementNumber());
    }

}
