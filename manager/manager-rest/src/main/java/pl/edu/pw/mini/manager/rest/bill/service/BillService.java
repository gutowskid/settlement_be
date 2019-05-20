package pl.edu.pw.mini.manager.rest.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManager;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManagerRepository;
import pl.edu.pw.mini.manager.rest.bill.domain.EmployeeManager;
import pl.edu.pw.mini.model.bill.BillDto;
import pl.edu.pw.mini.model.bill.BillStatus;

import java.util.List;

import static pl.edu.pw.mini.manager.rest.common.ErrorCode.*;

@Service
public class BillService {

    @Autowired
    private BillManagerRepository repository;

    @Autowired
    private BillDtoAssembler dtoAssembler;

    public List<BillDto> findActualBills(String managerId) {
        List<BillManager> bills = repository.findByManagers_managerIdContainsAndStatus(managerId, BillStatus.SENT);
        return dtoAssembler.toDtoList(bills);
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
