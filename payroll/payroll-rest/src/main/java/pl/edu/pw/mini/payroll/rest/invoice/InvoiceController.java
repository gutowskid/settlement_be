package pl.edu.pw.mini.payroll.rest.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.aws.FileDto;
import pl.edu.pw.mini.model.invoice.InvoiceDto;
import pl.edu.pw.mini.payroll.api.invoice.InvoiceDetailsDto;
import pl.edu.pw.mini.payroll.rest.invoice.service.InvoiceService;

import java.io.IOException;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @RequestMapping(path = "/invoice/actual", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDto> actualInvoices(@RequestBody JsonListRequest request) {
        return service.actualInvoices(request);
    }

    @RequestMapping(path = "/invoice/archived", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDto> archivedInvoices(@RequestBody JsonListRequest request) {
        return service.archivedInvoices(request);
    }

    @RequestMapping(path = "/invoice/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public InvoiceDetailsDto getInvoice(@PathVariable Long id) {
        return service.getInvoice(id);
    }

    @RequestMapping(path = "/invoice/{id}/attachment", method = RequestMethod.GET, produces = "application/json")
    public FileDto getInvoiceAttachment(@PathVariable Long id) throws IOException {
        return null; //TODO MOCK!
    }

    @RequestMapping(path = "/invoice/{id}/mark-processed", method = RequestMethod.GET, produces = "application/json")
    public void markInvoiceProcessed(@PathVariable Long id) {
        service.markInvoiceProcessed(id);
    }
}
