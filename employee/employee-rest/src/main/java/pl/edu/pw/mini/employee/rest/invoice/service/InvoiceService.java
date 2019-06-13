package pl.edu.pw.mini.employee.rest.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.core.aws.AwsS3Service;
import pl.edu.pw.mini.core.tools.MockLogger;
import pl.edu.pw.mini.core.tools.StringUtils;
import pl.edu.pw.mini.employee.api.invoice.AddInvoiceDto;
import pl.edu.pw.mini.employee.rest.accountnumber.service.AccountNumberService;
import pl.edu.pw.mini.employee.rest.invoice.domain.Invoice;
import pl.edu.pw.mini.employee.rest.invoice.domain.InvoiceRepository;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.aws.FileDto;
import pl.edu.pw.mini.model.invoice.InvoiceDto;
import pl.edu.pw.mini.model.invoice.InvoiceStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static pl.edu.pw.mini.employee.rest.common.ErrorCode.*;

@Service
public class InvoiceService {

    @Autowired
    private AwsS3Service s3Service;

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private InvoiceDtoAssembler assembler;

    @Autowired
    private AccountNumberService accountNumberService;

    public JsonListChunk<InvoiceDto> getActualInvoices(String employeeId, JsonListRequest request) {
        Page<Invoice> page = repository.findByEmployeeIdAndArchived(employeeId, false, PageRequest.of(request.getPageNumber(),request.getPageSize()));
        return new JsonListChunk<>(
                assembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public JsonListChunk<InvoiceDto> getArchivedInvoices(String employeeId, JsonListRequest request) {
        Page<Invoice> page = repository.findByEmployeeIdAndArchived(employeeId, true, PageRequest.of(request.getPageNumber(),request.getPageSize()));
        return new JsonListChunk<>(
                assembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public FileDto getInvoiceAttachment(String employeeId, Long invoiceId) throws IOException {
        Invoice invoice = repository.findById(invoiceId).orElseThrow(() -> EMP_0009);
        if(!invoice.getEmployeeId().equals(employeeId)) {
            throw EMP_0010;
        }

        return FileDto
                .builder()
                .content(s3Service.getFile(invoiceId))
                .name(invoice.getFileName())
                .build();
    }

    public Long addInvoice(String employeeId, AddInvoiceDto addInvoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setEmployeeId(employeeId);
        invoice.setAmount(addInvoiceDto.getAmount());
        invoice.setCreationDate(LocalDateTime.now());
        invoice.setUpdateDate(LocalDateTime.now());
        invoice.setStatus(InvoiceStatus.SENT);
        invoice.setSettlementNumber(addInvoiceDto.getSettlementNumber());
        invoice.setAccountNumber(accountNumberService.getMyAccountNumber(employeeId).getValue());
        invoice.setArchived(false);
        invoice.setFileName(addInvoiceDto.getFileDto().getName());
        invoice = repository.save(invoice);
        s3Service.addFile(addInvoiceDto.getFileDto(), invoice.getId());
        return invoice.getId();
    }

    public void editInvoice(String employeeId, Long id, AddInvoiceDto addInvoiceDto) {
        Invoice invoice = repository.findById(id).orElseThrow(() -> EMP_0011);
        if(!invoice.getEmployeeId().equals(employeeId)) {
            throw EMP_0012;
        }
        if (Stream.of(InvoiceStatus.SENT, InvoiceStatus.PROCESSED, InvoiceStatus.ACCEPTED).anyMatch(s -> s == invoice.getStatus())) {
            throw EMP_0013;
        }
        Optional.of(addInvoiceDto).map(AddInvoiceDto::getAmount).ifPresent(invoice::setAmount);
        invoice.setUpdateDate(LocalDateTime.now());
        invoice.setStatus(InvoiceStatus.SENT);
        Optional.of(addInvoiceDto).map(AddInvoiceDto::getSettlementNumber).filter(StringUtils::notEmpty).ifPresent(invoice::setSettlementNumber);
        invoice.setAccountNumber(accountNumberService.getMyAccountNumber(employeeId).getValue());
        Optional.of(addInvoiceDto).map(AddInvoiceDto::getFileDto).ifPresent(file -> {
            s3Service.deleteFile(invoice.getId());
            s3Service.addFile(file, invoice.getId());
            invoice.setFileName(file.getName());
        });
        repository.save(invoice);
    }
}
