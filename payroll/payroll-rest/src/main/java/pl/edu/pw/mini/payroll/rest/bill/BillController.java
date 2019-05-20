package pl.edu.pw.mini.payroll.rest.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.payroll.api.BillAccountNumberDto;
import pl.edu.pw.mini.payroll.rest.bill.service.BillService;

@RestController
public class BillController {

    @Autowired
    private BillService service;

    @RequestMapping(path = "bill/actual", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<BillAccountNumberDto> getActualBills(@RequestBody JsonListRequest request) {
        return service.getActualBills(request);
    }

    @RequestMapping(path = "bill/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public BillAccountNumberDto getBillById(@PathVariable Long id) {
        return service.getBillById(id);
    }

    @RequestMapping(path = "/bill/{id}/mark-processed", method = RequestMethod.PUT, produces = "application/json")
    public void markProcessedBill(@PathVariable Long id) {
        service.markProcessedBill(id);
    }

    @RequestMapping(path = "bill/archived", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<BillAccountNumberDto> getArchivedBills(@RequestBody JsonListRequest request) {
        return service.getArchivedBills(request);
    }
}
