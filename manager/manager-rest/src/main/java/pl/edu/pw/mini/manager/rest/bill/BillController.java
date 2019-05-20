package pl.edu.pw.mini.manager.rest.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.manager.rest.bill.service.BillService;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.bill.BillDto;

@RestController
public class BillController {

    @Autowired
    private BillService service;

    @RequestMapping(path = "/bill/actual", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<BillDto> findActualBills(@RequestBody JsonListRequest request) {
        return service.findActualBills(ContextService.getContext().getUserId(), request);
    }

    @RequestMapping(path = "/bill/archived", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<BillDto> findArchivedBills(@RequestBody JsonListRequest request) {
        return service.findArchivedBills(ContextService.getContext().getUserId(), request);
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
