package pl.edu.pw.mini.payroll.rest.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.aws.FileDto;
import pl.edu.pw.mini.payroll.api.invoice.InvoiceDetailsDto;
import pl.edu.pw.mini.payroll.rest.invoice.service.InvoiceService;

import java.io.IOException;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Secured(Constants.PAYROLL_ROLE)
    @RequestMapping(path = "/invoice/actual", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDetailsDto> actualInvoices(@RequestBody JsonListRequest request) {
        return service.actualInvoices(request);
    }

    @Secured(Constants.PAYROLL_ROLE)
    @RequestMapping(path = "/invoice/archived", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDetailsDto> archivedInvoices(@RequestBody JsonListRequest request) {
        return service.archivedInvoices(request);
    }

    @Secured(Constants.PAYROLL_ROLE)
    @RequestMapping(path = "/invoice/{id}/show", method = RequestMethod.GET, produces = "application/json")
    public InvoiceDetailsDto getInvoice(@PathVariable Long id) {
        return service.getInvoice(id);
    }

    @Secured(Constants.PAYROLL_ROLE)
    @RequestMapping(path = "/invoice/{id}/attachment", method = RequestMethod.GET, produces = "application/json")
    public FileDto getInvoiceAttachment(@PathVariable Long id) throws IOException {
        return service.getInvoiceAttachment(id);
    }

    @Secured(Constants.PAYROLL_ROLE)
    @RequestMapping(path = "/invoice/{id}/mark-processed", method = RequestMethod.PUT, produces = "application/json")
    public void markInvoiceProcessed(@PathVariable Long id) {
        service.markInvoiceProcessed(id);
    }
}
