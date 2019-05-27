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

import java.util.stream.Stream;

import static pl.edu.pw.mini.payroll.rest.common.ErrorCode.*;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private InvoiceDtoAssembler dtoAssembler;

    public JsonListChunk<InvoiceDto> actualInvoices(JsonListRequest request) {
        Page<Invoice> page = repository.findByStatus(InvoiceStatus.ACCEPTED, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public JsonListChunk<InvoiceDto> archivedInvoices(JsonListRequest request) {
        Page<Invoice> page = repository.findByStatus(InvoiceStatus.PROCESSED, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public void markInvoiceProcessed(Long invoiceId) {
        Invoice invoice = repository.findById(invoiceId).orElseThrow(() -> PAY_0003);
        if(invoice.getStatus() != InvoiceStatus.SENT) {
            throw PAY_0005;
        }
        invoice.setStatus(InvoiceStatus.PROCESSED);
        invoice.setArchived(true);
        repository.save(invoice);
    }

    public InvoiceDetailsDto getInvoice(Long invoiceId) {
        Invoice invoice = repository.findById(invoiceId).orElseThrow(() -> PAY_0004);
        if(Stream.of(InvoiceStatus.SENT, InvoiceStatus.PROCESSED).noneMatch(i -> i == invoice.getStatus())) {
            throw PAY_0006;
        }
        return dtoAssembler.toDetailsDto(invoice);
    }

}
