package pl.edu.pw.mini.employee.rest.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.employee.api.bill.CreateBillDto;
import pl.edu.pw.mini.employee.api.hours.HoursDto;
import pl.edu.pw.mini.employee.rest.accountnumber.service.AccountNumberService;
import pl.edu.pw.mini.employee.rest.bill.domain.Bill;
import pl.edu.pw.mini.employee.rest.bill.domain.BillRepository;
import pl.edu.pw.mini.employee.rest.hours.service.HoursService;
import pl.edu.pw.mini.employee.rest.salary.service.EmployeeSalaryService;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.Period;
import pl.edu.pw.mini.model.bill.BillDto;
import pl.edu.pw.mini.model.bill.BillStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

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
        Long count = repository.countByEmployeeIdAndProcessed(employeeId, false);
        if(count > 0) {
            throw EMP_0007;
        }
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

    public BillDto getActualBill(String employeeId) {
        Bill bill = repository.findFirstByEmployeeIdAndProcessed(employeeId, false).orElseThrow(() -> EMP_0008);
        return billDtoAssembler.toDto(bill);
    }

    public JsonListChunk<BillDto> getArchivedBills(String employeeId, JsonListRequest request) {
        Page<Bill> page = repository.findByEmployeeIdAndProcessed(employeeId, true, PageRequest.of(request.getPageNumber(), request.getPageSize()));
        return new JsonListChunk<>(
                billDtoAssembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
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

        if (Stream.of(BillStatus.SENT, BillStatus.PROCESSED).anyMatch(s -> s == bill.getStatus())) {
            throw EMP_0006;
        }
        
        generateBill(employeeId, createBillDto, bill);
        repository.save(bill);
        return billDtoAssembler.toDto(bill);
    }
}
