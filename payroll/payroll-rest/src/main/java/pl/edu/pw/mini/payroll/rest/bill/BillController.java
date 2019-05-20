package pl.edu.pw.mini.payroll.rest.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.payroll.api.BillAccountNumberDto;
import pl.edu.pw.mini.payroll.rest.bill.service.BillService;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService service;

    @RequestMapping(path = "bill/list", method = RequestMethod.GET, produces = "application/json")
    public List<BillAccountNumberDto> getActualBills() {
        return service.getActualBills();
    }

    @RequestMapping(path = "bill/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public BillAccountNumberDto getBillById(@PathVariable Long id) {
        return service.getBillById(id);
    }

    @RequestMapping(path = "/bill/{id}/mark-processed", method = RequestMethod.PUT, produces = "application/json")
    public void markProcessedBill(@PathVariable Long id) {
        service.markProcessedBill(id);
    }
}
