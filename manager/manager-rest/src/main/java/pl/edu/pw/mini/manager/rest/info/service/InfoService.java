package pl.edu.pw.mini.manager.rest.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.core.tools.MockLogger;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManagerRepository;
import pl.edu.pw.mini.manager.rest.employeemanager.domain.EmployeeManagerRepository;
import pl.edu.pw.mini.model.bill.BillStatus;
import pl.gutowskid.manager.api.info.EmployeeInfoDto;
import pl.gutowskid.manager.api.info.SummaryDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InfoService {

    @Autowired
    private BillManagerRepository billManagerRepository;

    @Autowired
    private EmployeeManagerRepository employeeManagerRepository;

    public SummaryDto getSummary(String managerId) {
        SummaryDto dto = new SummaryDto();
        dto.setBillCount(billManagerRepository.countByManagers_managerIdContainsAndStatus(managerId, BillStatus.SENT));
        dto.setInvoicesCount(MockLogger.getLong(3L));
        dto.setEmployeesCount(employeeManagerRepository.countByManagerIdAndUser_isActive(managerId, true));
        return dto;
    }

    public List<EmployeeInfoDto> getEmployeesInfo(String managerId) {
        LocalDateTime after = LocalDate.now().minusMonths(1).withDayOfMonth(1).atStartOfDay();
        LocalDateTime before = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        return billManagerRepository.getEmployeeInfo(managerId, BillStatus.PROCESSED, after, before);
    }
}
