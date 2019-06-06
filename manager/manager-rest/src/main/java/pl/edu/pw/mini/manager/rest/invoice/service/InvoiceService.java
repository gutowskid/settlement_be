package pl.edu.pw.mini.manager.rest.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.manager.rest.employeemanager.domain.EmployeeManager;
import pl.edu.pw.mini.manager.rest.invoice.domain.InvoiceManager;
import pl.edu.pw.mini.manager.rest.invoice.domain.InvoiceManagerRepository;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.invoice.InvoiceDto;
import pl.edu.pw.mini.model.invoice.InvoiceStatus;

import static pl.edu.pw.mini.manager.rest.common.ErrorCode.*;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceManagerRepository repository;

    @Autowired
    private InvoiceDtoAssembler dtoAssembler;

    public JsonListChunk<InvoiceDto> actualInvoices(String managerId, JsonListRequest request) {
        Page<InvoiceManager> page = repository.findByManagers_managerIdContainsAndStatus(managerId, InvoiceStatus.SENT, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
                );
    }

    public JsonListChunk<InvoiceDto> archivedInvoices(String managerId, JsonListRequest request) {
        Page<InvoiceManager> page = repository.findByManagers_managerIdContainsAndStatusIsNot(managerId, InvoiceStatus.SENT, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public void acceptInvoice(String managerId, Long invoiceId) {
        InvoiceManager invoice = findManagerInvoice(managerId, invoiceId);
        checkIfInvoiceRequireAction(invoice);
        invoice.setAcceptorId(managerId);
        invoice.setStatus(InvoiceStatus.ACCEPTED);
        repository.save(invoice);
    }

    public void rejectInvoice(String managerId, Long invoiceId) {
        InvoiceManager invoice = findManagerInvoice(managerId, invoiceId);
        checkIfInvoiceRequireAction(invoice);
        invoice.setStatus(InvoiceStatus.REJECTED);
        repository.save(invoice);
    }

    public InvoiceDto getInvoice(String managerId, Long invoiceId) {
        InvoiceManager invoice = findManagerInvoice(managerId, invoiceId);
        return dtoAssembler.toDto(invoice);
    }

    private InvoiceManager findManagerInvoice(String managerId, Long invoiceId) {
        InvoiceManager invoice = repository.findById(invoiceId).orElseThrow(() -> MAN_0004);
        if(invoice.getManagers().stream().map(EmployeeManager::getManagerId).noneMatch(m -> m.equals(managerId))) {
            throw MAN_0006;
        }
        return invoice;
    }
    private void checkIfInvoiceRequireAction(InvoiceManager invoice) {
        if(invoice.getStatus() != InvoiceStatus.SENT) {
            throw MAN_0005;
        }
    }
}
