package pl.edu.pw.mini.employee.rest.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.core.security.authorization.AllowAll;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.employee.rest.bill.service.BillService;
import pl.edu.pw.mini.model.bill.BillDto;
import pl.edu.pw.mini.employee.api.bill.CreateBillDto;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService service;

    @AllowAll
    @RequestMapping(value = "/bill/generate", method = RequestMethod.POST, produces = "application/json")
    public BillDto generateBill(@RequestBody CreateBillDto createBillDto) {
        return service.generateBill(ContextService.getContext().getUserId(), createBillDto);
    }
    @AllowAll
    @RequestMapping(value = "/bill/list", method = RequestMethod.GET, produces = "application/json")
    public List<BillDto> getMyBills(Boolean processed) {
        return service.getMyBills(ContextService.getContext().getUserId(), processed);
    }

    @AllowAll
    @RequestMapping(value = "/bill/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public BillDto getBillById(@PathVariable Long id) {
        return service.getBillById(ContextService.getContext().getUserId(), id);
    }

    @AllowAll
    @RequestMapping(value = "/bill/{id}/send", method = RequestMethod.PUT, produces = "application/json")
    public void sendBill(@PathVariable Long id) {
        service.sendBill(ContextService.getContext().getUserId(), id);
    }

    @AllowAll
    @RequestMapping(value = "/bill/{id}/update", method = RequestMethod.PUT, produces = "application/json")
    public BillDto updateBill(@PathVariable Long id, @RequestBody CreateBillDto createBillDto) {
        return service.updateBill(ContextService.getContext().getUserId(), id, createBillDto);
    }
}
