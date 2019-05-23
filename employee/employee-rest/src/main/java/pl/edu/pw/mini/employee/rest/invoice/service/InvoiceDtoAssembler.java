package pl.edu.pw.mini.employee.rest.invoice.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.invoice.BaseInvoiceDtoAssembler;
import pl.edu.pw.mini.employee.rest.invoice.domain.Invoice;
import pl.edu.pw.mini.model.invoice.InvoiceDto;

@Component
public class InvoiceDtoAssembler extends BaseInvoiceDtoAssembler<Invoice, InvoiceDto> {

    @Override
    public InvoiceDto toDto(Invoice input) {
        InvoiceDto dto = new InvoiceDto();
        fill(input, dto);
        return dto;
    }
}
