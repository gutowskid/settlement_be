package pl.edu.pw.mini.payroll.rest.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.bill.BillStatus;
import pl.edu.pw.mini.payroll.api.bill.BillAccountNumberDto;
import pl.edu.pw.mini.payroll.rest.bill.domain.Bill;
import pl.edu.pw.mini.payroll.rest.bill.domain.BillRepository;

import java.util.stream.Stream;

import static pl.edu.pw.mini.payroll.rest.common.ErrorCode.*;

@Service
public class BillService {

    @Autowired
    private BillAssembler assembler;

    @Autowired
    private BillRepository repository;

    public JsonListChunk<BillAccountNumberDto> getActualBills(JsonListRequest request) {
        Page<Bill> page = repository.findByStatus(BillStatus.ACCEPTED, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                assembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public JsonListChunk<BillAccountNumberDto> getArchivedBills(JsonListRequest request) {
        Page<Bill> page = repository.findByStatus(BillStatus.PROCESSED, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                assembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
                );
    }

    public BillAccountNumberDto getBillById(Long id) {
        Bill bill = repository.findById(id).orElseThrow(() -> PAY_0001);
        if(Stream.of(BillStatus.SENT, BillStatus.PROCESSED).noneMatch(b -> b == bill.getStatus())) {
            throw PAY_0007;
        }
        return assembler.toDto(bill);
    }

    public void markProcessedBill(Long id) {
        Bill bill = repository.findById(id).orElseThrow(() -> PAY_0002);
        if(bill.getStatus() != BillStatus.ACCEPTED) {
            throw PAY_0008;
        }
        bill.setStatus(BillStatus.PROCESSED);
        bill.setProcessed(true);
        repository.save(bill);
    }

}
