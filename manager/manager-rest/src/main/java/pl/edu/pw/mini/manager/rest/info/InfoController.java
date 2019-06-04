package pl.edu.pw.mini.manager.rest.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.manager.rest.info.service.InfoService;
import pl.gutowskid.manager.api.info.EmployeeInfoDto;
import pl.gutowskid.manager.api.info.SummaryDto;

import java.util.List;

@RestController
public class InfoController {

    @Autowired
    private InfoService service;

    @Secured(Constants.MANAGER_ROLE)
    @RequestMapping(path = "/info/summary", method = RequestMethod.GET, produces = "application/json")
    public SummaryDto getSummary() {
        return service.getSummary(ContextService.getContext().getUserId());
    }

    @Secured(Constants.MANAGER_ROLE)
    @RequestMapping(path = "/info/employees", method = RequestMethod.GET, produces = "application/json")
    public List<EmployeeInfoDto> getEmployeesInfo() {
        return service.getEmployeesInfo(ContextService.getContext().getUserId());
    }
}
