package pl.edu.pw.mini.payroll.rest.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.model.salary.SalaryDto;
import pl.edu.pw.mini.payroll.rest.salary.domain.Salary;
import pl.edu.pw.mini.payroll.rest.salary.domain.SalaryRepository;

import java.util.List;

@Service
public class PayrollSalaryService {

    @Autowired
    private SalaryRepository repository;

    @Autowired
    private SalaryDtoAssembler assembler;

    public List<SalaryDto> getEmployeesSalary() { //TODO paging
        List<Salary> list = repository.findAll();
        return assembler.toDtoList(list);
    }

    public void defineEmployeeSalary(SalaryDto dto) {
        Salary salary = repository.findById(dto.getEmployeeId()).orElse(new Salary());
        salary.setEmployeeId(dto.getEmployeeId());
        salary.setSalary(dto.getSalary());
        repository.save(salary);
    }

}
