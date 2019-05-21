package pl.edu.pw.mini.manager.rest.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManager;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManagerRepository;
import pl.edu.pw.mini.manager.rest.employeemanager.domain.EmployeeManager;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.bill.BillDto;
import pl.edu.pw.mini.model.bill.BillStatus;

import static pl.edu.pw.mini.manager.rest.common.ErrorCode.*;

@Service
public class BillService {

    @Autowired
    private BillManagerRepository repository;

    @Autowired
    private BillDtoAssembler dtoAssembler;

    public JsonListChunk<BillDto> findActualBills(String managerId, JsonListRequest request) {
        Page<BillManager> page = repository.findByManagers_managerIdContainsAndStatus(managerId, BillStatus.SENT, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public JsonListChunk<BillDto> findArchivedBills(String managerId, JsonListRequest request) {
        Page<BillManager> page = repository.findByManagers_managerIdContainsAndStatus(managerId, BillStatus.SENT, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                dtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public void acceptBill(String managerId, Long billId) {
        BillManager bill = findBIllById(managerId, billId);
        checkBillStatus(bill);
        bill.setStatus(BillStatus.ACCEPTED);
        repository.save(bill);
    }

    public void rejectBill(String managerId, Long billId) {
        BillManager bill = findBIllById(managerId, billId);
        checkBillStatus(bill);
        bill.setStatus(BillStatus.REJECTED);
        repository.save(bill);
    }

    public BillDto showBill(String managerId, Long billId) {
        BillManager bill = findBIllById(managerId, billId);
        return dtoAssembler.toDto(bill);
    }

    private BillManager findBIllById(String managerId, Long billId) {
        BillManager bill = repository.findById(billId).orElseThrow(() -> MAN_0001);
        if(bill.getManagers().stream().map(EmployeeManager::getManagerId).noneMatch(u -> u.equals(managerId))) {
            throw MAN_0002;
        }
        return bill;
    }

    private void checkBillStatus(BillManager bill) {
        if(bill.getStatus() != BillStatus.SENT) {
            throw MAN_0003;
        }
    }
}
