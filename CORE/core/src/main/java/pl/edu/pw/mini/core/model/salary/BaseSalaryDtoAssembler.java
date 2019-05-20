package pl.edu.pw.mini.core.model.salary;

import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.model.salary.SalaryDto;

public abstract class BaseSalaryDtoAssembler<X extends BaseSalary, Z extends SalaryDto> extends DtoAssembler<X,Z> {

    protected void fill(X input, Z dto) {
        dto.setEmployeeId(input.getEmployeeId());
        dto.setSalary(input.getSalary());
    }
}
