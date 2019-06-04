package pl.edu.pw.mini.employee.rest.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.security.authorization.AllowAll;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.employee.api.bill.CreateBillDto;
import pl.edu.pw.mini.employee.rest.bill.service.BillService;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.bill.BillDto;

@RestController
public class BillController {

    @Autowired
    private BillService service;

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(value = "/bill/generate", method = RequestMethod.POST, produces = "application/json")
    public BillDto generateBill(@RequestBody CreateBillDto createBillDto) {
        return service.generateBill(ContextService.getContext().getUserId(), createBillDto);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(value = "/bill/actual", method = RequestMethod.GET, produces = "application/json")
    public BillDto getActualBill() {
        return service.getActualBill(ContextService.getContext().getUserId());
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(value = "/bill/archived", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<BillDto> getArchivedBills(@RequestBody JsonListRequest request) {
        return service.getArchivedBills(ContextService.getContext().getUserId(), request);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(value = "/bill/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public BillDto getBillById(@PathVariable Long id) {
        return service.getBillById(ContextService.getContext().getUserId(), id);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(value = "/bill/{id}/send", method = RequestMethod.PUT, produces = "application/json")
    public void sendBill(@PathVariable Long id) {
        service.sendBill(ContextService.getContext().getUserId(), id);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(value = "/bill/{id}/update", method = RequestMethod.PUT, produces = "application/json")
    public BillDto updateBill(@PathVariable Long id, @RequestBody CreateBillDto createBillDto) {
        return service.updateBill(ContextService.getContext().getUserId(), id, createBillDto);
    }
}
