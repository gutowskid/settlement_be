package pl.edu.pw.mini.payroll.rest.salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.model.salary.SalaryDto;
import pl.edu.pw.mini.payroll.rest.salary.service.PayrollSalaryService;

import java.util.List;

@RestController
public class PayrollSalaryController {

    @Autowired
    private PayrollSalaryService service;


    @RequestMapping(value = "/salary/list", method = RequestMethod.POST, produces = "application/json")
    public List<SalaryDto> getEmployeesSalary() { //TODO paging
        return service.getEmployeesSalary();
    }

    @RequestMapping(value = "/salary/define", method = RequestMethod.PUT, produces = "application/json")
    public void defineEmployeeSalary(SalaryDto dto) {
        service.defineEmployeeSalary(dto);
    }
}
