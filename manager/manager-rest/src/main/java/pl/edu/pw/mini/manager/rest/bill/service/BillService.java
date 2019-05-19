package pl.edu.pw.mini.manager.rest.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManager;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManagerRepository;
import pl.edu.pw.mini.model.bill.BillDto;
import pl.edu.pw.mini.model.bill.BillStatus;

import java.util.List;

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
}
