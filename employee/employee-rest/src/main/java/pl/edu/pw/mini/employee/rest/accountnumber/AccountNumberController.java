package pl.edu.pw.mini.employee.rest.accountnumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.security.authorization.AllowAll;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.core.tools.StringWrapper;
import pl.edu.pw.mini.employee.rest.accountnumber.service.AccountNumberService;
import pl.edu.pw.mini.model.UserRole;

@RestController
public class AccountNumberController {

    @Autowired
    private AccountNumberService service;
    
    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(path = "/accountnumber", method = RequestMethod.GET, produces = "application/json")
    public StringWrapper getMyAccountNumber() {
        return service.getMyAccountNumber(ContextService.getContext().getUserId());
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(path = "/accountnumber", method = RequestMethod.POST, produces = "application/json")
    public void setMyAccountNumber(String number) {
        service.setMyAccountNumber(ContextService.getContext().getUserId(), number);
    }
}
