package pl.edu.pw.mini.employee.rest.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.employee.rest.accountnumber.service.AccountNumberService;
import pl.edu.pw.mini.employee.rest.bill.domain.Bill;
import pl.edu.pw.mini.employee.rest.bill.domain.BillRepository;

import pl.edu.pw.mini.employee.rest.hours.service.HoursService;
import pl.edu.pw.mini.employee.rest.salary.service.EmployeeSalaryService;
import pl.edu.pw.mini.model.Period;
import pl.gutowskid.employee.api.bill.BillDto;
import pl.gutowskid.employee.api.bill.BillStatus;
import pl.gutowskid.employee.api.bill.CreateBillDto;
import pl.gutowskid.employee.api.hours.HoursDto;

import java.time.LocalDateTime;
import java.util.List;

import static pl.edu.pw.mini.employee.rest.common.ErrorCode.*;

@Service
public class BillService {

    @Autowired
    private HoursService hoursService;

    @Autowired
    private EmployeeSalaryService salaryService;

    @Autowired
    private AccountNumberService accountNumberService;

    @Autowired
    private BillDtoAssembler billDtoAssembler;

    @Autowired
    private BillRepository repository;

    public BillDto generateBill(String employeeId, CreateBillDto createBillDto) {
        Bill bill = generateBill(employeeId, createBillDto, new Bill());
        repository.save(bill);
        return billDtoAssembler.toDto(bill);
    }

    private Bill generateBill(String employeeId, CreateBillDto createBillDto, Bill bill) {
        bill.setEmployeeId(employeeId);
        List<HoursDto> hours = hoursService.getMyDayHours(employeeId, Period.builder().from(createBillDto.getFrom()).to(createBillDto.getTo()).build());
        bill.setHours(hours.stream().map(HoursDto::getCount).mapToLong(Long::longValue).sum());
        bill.setSalary(salaryService.getMySalary(employeeId));
        bill.setBrutto(bill.getHours() * bill.getSalary());
        bill.setIncomeCosts(bill.getBrutto() * 0.2);
        bill.setTax((bill.getBrutto() - bill.getIncomeCosts()) * 0.18);
        bill.setNetto(bill.getBrutto() - bill.getTax());
        bill.setCreationDate(LocalDateTime.now());
        bill.setUpdateDate(LocalDateTime.now());
        bill.setStatus(BillStatus.SAVED);
        bill.setSettlementNumber(createBillDto.getSettlementNumber());
        bill.setAccountNumber(accountNumberService.getMyAccountNumber(employeeId).getValue());
        bill.setProcessed(false);
        bill.setFromDate(createBillDto.getFrom());
        bill.setToDate(createBillDto.getTo());
        return bill;
    }

    public List<BillDto> getMyBills(String employeeId, Boolean processed) {
        return billDtoAssembler.toDtoList(repository.findByEmployeeIdAndProcessed(employeeId, processed));
    }

    public BillDto getBillById(String employeeId, Long id) {
        return billDtoAssembler.toDto(findBillById(employeeId, id));
    }

    private Bill findBillById(String employeeId, Long id) {
        Bill bill = repository.findById(id).orElseThrow(() -> EMP_0004);
        if(!bill.getEmployeeId().equals(employeeId)) {
            throw EMP_0005;
        }
        return bill;
    }

    public void sendBill(String employeeId, Long id) {
        Bill bill = findBillById(employeeId, id);
        bill.setStatus(BillStatus.SENT);
        repository.save(bill);
    }

    public BillDto updateBill(String employeeId, Long id, CreateBillDto createBillDto) {
        Bill bill = findBillById(employeeId, id);
        generateBill(employeeId, createBillDto, bill);
        repository.save(bill);
        return billDtoAssembler.toDto(bill);
    }
}
