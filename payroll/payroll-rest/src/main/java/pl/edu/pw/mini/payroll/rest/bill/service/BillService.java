package pl.edu.pw.mini.payroll.rest.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.model.bill.BillStatus;
import pl.edu.pw.mini.payroll.api.BillAccountNumberDto;
import pl.edu.pw.mini.payroll.rest.bill.domain.Bill;
import pl.edu.pw.mini.payroll.rest.bill.domain.BillRepository;

import java.util.List;

import static pl.edu.pw.mini.payroll.rest.common.ErrorCode.PAY_0001;
import static pl.edu.pw.mini.payroll.rest.common.ErrorCode.PAY_0002;

@Service
public class BillService {

    @Autowired
    private BillAssembler assembler;

    @Autowired
    private BillRepository repository;

    public List<BillAccountNumberDto> getActualBills() {
        List<Bill> list = repository.findByStatus(BillStatus.ACCEPTED);
        return assembler.toDtoList(list);
    }

    public BillAccountNumberDto getBillById(Long id) {
        return assembler.toDto(repository.findById(id).orElseThrow(() -> PAY_0001));
    }

    public void markProcessedBill(Long id) {
        Bill bill = repository.findById(id).orElseThrow(() -> PAY_0002);
        bill.setStatus(BillStatus.PROCESSED);
        bill.setProcessed(true);
        repository.save(bill);
    }

}
