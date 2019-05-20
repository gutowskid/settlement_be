package pl.edu.pw.mini.manager.rest.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.manager.rest.bill.service.BillService;
import pl.edu.pw.mini.model.bill.BillDto;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService service;

    @RequestMapping(path = "/bill/actual", method = RequestMethod.GET, produces = "application/json")
    public List<BillDto> findActualBills() {
        return service.findActualBills(ContextService.getContext().getUserId());
    }

    @RequestMapping(path = "/bill/{id}/accept", method = RequestMethod.PUT, produces = "application/json")
    public void acceptBill(@PathVariable Long id) {
        service.acceptBill(ContextService.getContext().getUserId(), id);
    }

    @RequestMapping(path = "/bill/{id}/reject", method = RequestMethod.PUT, produces = "application/json")
    public void rejectBill(@PathVariable Long id) {
        service.rejectBill(ContextService.getContext().getUserId(), id);
    }

    @RequestMapping(path = "/bill/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public BillDto showBill(@PathVariable Long id) {
        return service.showBill(ContextService.getContext().getUserId(), id);
    }

}
