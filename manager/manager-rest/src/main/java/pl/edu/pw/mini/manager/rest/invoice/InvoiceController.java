package pl.edu.pw.mini.manager.rest.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.manager.rest.invoice.service.InvoiceService;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.aws.FileDto;
import pl.edu.pw.mini.model.invoice.InvoiceDto;

import java.io.IOException;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @RequestMapping(path = "/invoice/actual", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDto> actualInvoices(@RequestBody JsonListRequest request) {
        return service.actualInvoices(ContextService.getContext().getUserId(), request);
    }

    @RequestMapping(path = "/invoice/archived", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDto> archivedInvoices(@RequestBody JsonListRequest request) {
        return service.archivedInvoices(ContextService.getContext().getUserId(), request);
    }


    @RequestMapping(path = "/invoice/{id}/accept", method = RequestMethod.PUT, produces = "application/json")
    public void acceptInvoice(@PathVariable Long id) {
        service.acceptInvoice(ContextService.getContext().getUserId(), id);
    }

    @RequestMapping(path = "/invoice/{id}/reject", method = RequestMethod.PUT, produces = "application/json")
    public void rejectInvoice(@PathVariable Long id) {
        service.rejectInvoice(ContextService.getContext().getUserId(), id);
    }

    @RequestMapping(path = "/invoice/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public InvoiceDto getInvoice(@PathVariable Long id) {
        return service.getInvoice(ContextService.getContext().getUserId(), id);
    }

    @RequestMapping(path = "/invoice/{id}/attachment", method = RequestMethod.GET, produces = "application/json")
    public FileDto getInvoiceAttachment(@PathVariable Long id) throws IOException {
        //return service.getInvoiceAttachment(ContextService.getContext().getUserId(), id);
        return null; //TODO MOCK!
    }
}
