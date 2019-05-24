package pl.edu.pw.mini.manager.rest.invoice.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.invoice.BaseInvoiceDtoAssembler;
import pl.edu.pw.mini.manager.rest.invoice.domain.InvoiceManager;
import pl.edu.pw.mini.model.invoice.InvoiceDto;

@Component
public class InvoiceDtoAssembler extends BaseInvoiceDtoAssembler<InvoiceManager, InvoiceDto> {

    @Override
    public InvoiceDto toDto(InvoiceManager input) {
        InvoiceDto dto = new InvoiceDto();
        fill(input, dto);
        return dto;
    }
}
