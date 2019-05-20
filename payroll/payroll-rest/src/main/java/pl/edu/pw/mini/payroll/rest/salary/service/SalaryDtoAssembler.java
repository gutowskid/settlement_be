package pl.edu.pw.mini.payroll.rest.salary.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.salary.BaseSalaryDtoAssembler;
import pl.edu.pw.mini.model.salary.SalaryDto;
import pl.edu.pw.mini.payroll.rest.salary.domain.Salary;

@Component
public class SalaryDtoAssembler extends BaseSalaryDtoAssembler<Salary, SalaryDto> {

    @Override
    public SalaryDto toDto(Salary input) {
        SalaryDto dto = new SalaryDto();
        fill(input, dto);
        return dto;
    }
}
