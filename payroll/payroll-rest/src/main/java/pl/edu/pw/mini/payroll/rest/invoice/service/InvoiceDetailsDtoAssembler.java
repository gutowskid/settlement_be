package pl.edu.pw.mini.payroll.rest.invoice.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.invoice.BaseInvoiceDtoAssembler;
import pl.edu.pw.mini.payroll.api.invoice.InvoiceDetailsDto;
import pl.edu.pw.mini.payroll.rest.invoice.domain.Invoice;

@Component
public class InvoiceDetailsDtoAssembler extends BaseInvoiceDtoAssembler<Invoice, InvoiceDetailsDto> {

    @Override
    public InvoiceDetailsDto toDto(Invoice input) {
        InvoiceDetailsDto dto = new InvoiceDetailsDto();
        fill(input, dto);
        dto.setAccountNumber(input.getAccountNumber());
        return dto;
    }
}
