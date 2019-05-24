package pl.edu.pw.mini.payroll.rest.invoice.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.invoice.BaseInvoiceDtoAssembler;
import pl.edu.pw.mini.model.invoice.InvoiceDto;
import pl.edu.pw.mini.payroll.api.invoice.InvoiceDetailsDto;
import pl.edu.pw.mini.payroll.rest.invoice.domain.Invoice;

@Component
public class InvoiceDtoAssembler extends BaseInvoiceDtoAssembler<Invoice, InvoiceDto> {

    @Override
    public InvoiceDto toDto(Invoice input) {
        InvoiceDto dto = new InvoiceDto();
        fill(input, dto);
        return dto;
    }

    public InvoiceDetailsDto toDetailsDto(Invoice input) {
        InvoiceDetailsDto dto = new InvoiceDetailsDto();
        fill(input, dto);
        dto.setAccountNumber(input.getAccountNumber());
        return dto;
    }
}
