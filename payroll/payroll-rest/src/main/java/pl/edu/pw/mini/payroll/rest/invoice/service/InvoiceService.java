package pl.edu.pw.mini.payroll.rest.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.invoice.InvoiceDto;
import pl.edu.pw.mini.model.invoice.InvoiceStatus;
import pl.edu.pw.mini.payroll.api.invoice.InvoiceDetailsDto;
import pl.edu.pw.mini.payroll.rest.invoice.domain.Invoice;
import pl.edu.pw.mini.payroll.rest.invoice.domain.InvoiceRepository;

import static pl.edu.pw.mini.payroll.rest.common.ErrorCode.PAY_0003;
import static pl.edu.pw.mini.payroll.rest.common.ErrorCode.PAY_0004;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private InvoiceDtoAssembler dtoAssembler;

    public JsonListChunk<InvoiceDto> actualInvoices(JsonListRequest request) {
        Page<Invoice> page = repository.findByArchived(false, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public JsonListChunk<InvoiceDto> archivedInvoices(JsonListRequest request) {
        Page<Invoice> page = repository.findByArchived(true, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public void markInvoiceProcessed(Long invoiceId) {
        Invoice invoice = repository.findById(invoiceId).orElseThrow(() -> PAY_0003);
        invoice.setStatus(InvoiceStatus.PROCESSED);
        invoice.setArchived(true);
        repository.save(invoice);
    }

    public InvoiceDetailsDto getInvoice(Long invoiceId) {
        Invoice invoice = repository.findById(invoiceId).orElseThrow(() -> PAY_0004);
        return dtoAssembler.toDetailsDto(invoice);
    }

}
