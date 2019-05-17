package pl.edu.pw.mini.employee.rest.salary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.security.authentication.Context;
import pl.edu.pw.mini.core.security.authorization.AllowAll;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.employee.rest.salary.service.EmployeeSalaryService;

@RestController
public class EmployeeSalaryController {

    @Autowired
    private EmployeeSalaryService salaryService;

    @AllowAll
    @RequestMapping(path = "/salary", method = RequestMethod.GET, produces = "application/json")
    public Long getMySalary() {
        Context context = ContextService.getContext();
        return salaryService.getMySalary(context.getUserId());
    }
}
