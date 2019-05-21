package pl.edu.pw.mini.manager.rest.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.core.tools.MockLogger;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManagerRepository;
import pl.edu.pw.mini.manager.rest.employeemanager.domain.EmployeeManagerRepository;
import pl.edu.pw.mini.model.bill.BillStatus;
import pl.gutowskid.manager.api.info.StatisticDto;

@Service
public class InfoService {

    @Autowired
    private BillManagerRepository billManagerRepository;

    @Autowired
    private EmployeeManagerRepository employeeManagerRepository;

    public StatisticDto getStatistic(String managerId) {
        StatisticDto dto = new StatisticDto();
        dto.setBillCount(billManagerRepository.countByManagers_managerIdContainsAndStatus(managerId, BillStatus.SENT));
        dto.setInvoicesCount(MockLogger.getLong(3L));
        dto.setEmployeesCount(employeeManagerRepository.countByManagerIdAndUser_isActive(managerId, true));
        return dto;
    }
}
