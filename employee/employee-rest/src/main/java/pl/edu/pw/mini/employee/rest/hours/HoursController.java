package pl.edu.pw.mini.employee.rest.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.security.authorization.AllowAll;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.employee.rest.hours.service.HoursService;
import pl.edu.pw.mini.model.Period;
import pl.gutowskid.employee.api.hours.HoursDto;

import java.util.List;

@RestController
public class HoursController {

    @Autowired
    private HoursService hoursService;

    @AllowAll
    @RequestMapping(path = "/hours/report", method = RequestMethod.POST, produces = "application/json")
    public void setDayHour(@RequestBody HoursDto dto) {
        hoursService.setDayHour(ContextService.getContext().getUserId(), dto);
    }

    @AllowAll
    @RequestMapping(path = "/hours/find", method = RequestMethod.POST, produces = "application/json")
    public List<HoursDto> getMyDayHours(@RequestBody Period period) {
        return hoursService.getMyDayHours(ContextService.getContext().getUserId(), period);
    }
}
