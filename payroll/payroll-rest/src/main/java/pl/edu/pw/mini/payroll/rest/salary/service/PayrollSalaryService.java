package pl.edu.pw.mini.payroll.rest.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.salary.SalaryDto;
import pl.edu.pw.mini.payroll.rest.salary.domain.Salary;
import pl.edu.pw.mini.payroll.rest.salary.domain.SalaryRepository;

@Service
public class PayrollSalaryService {

    @Autowired
    private SalaryRepository repository;

    @Autowired
    private SalaryDtoAssembler assembler;

    public JsonListChunk<SalaryDto> getEmployeeSalary(JsonListRequest request) {
        Page<Salary> page = repository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize()));

        return new JsonListChunk<> (
                assembler.toDtoList(page.get()),
                page.getTotalElements(),
                page.getTotalPages() > request.getPageNumber() + 1
        );
    }

    public void defineEmployeeSalary(SalaryDto dto) {
        Salary salary = repository.findById(dto.getEmployeeId()).orElse(new Salary());
        salary.setEmployeeId(dto.getEmployeeId());
        salary.setSalary(dto.getSalary());
        repository.save(salary);
    }

}
