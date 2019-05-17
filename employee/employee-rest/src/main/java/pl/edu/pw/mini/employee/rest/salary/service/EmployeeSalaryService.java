package pl.edu.pw.mini.employee.rest.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.employee.rest.salary.domain.SalaryRepository;

import static pl.edu.pw.mini.employee.rest.common.ErrorCode.EMP_0001;

@Service
public class EmployeeSalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public Long getMySalary(String employeeId) {
        return salaryRepository.findById(employeeId).orElseThrow(() -> EMP_0001).getSalary();
    }
}
