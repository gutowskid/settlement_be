package pl.edu.pw.mini.employee.rest.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.employee.api.invoice.AddInvoiceDto;
import pl.edu.pw.mini.employee.rest.invoice.service.InvoiceService;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.aws.FileDto;
import pl.edu.pw.mini.model.invoice.InvoiceDto;

import java.io.IOException;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(path = "/invoice/actual", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDto> getActualInvoices(@RequestBody JsonListRequest request) {
        return service.getActualInvoices(ContextService.getContext().getUserId(), request);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(path = "/invoice/archived", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<InvoiceDto> getArchivedInvoices(@RequestBody JsonListRequest request) {
        return service.getArchivedInvoices(ContextService.getContext().getUserId(), request);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(path = "/invoice/{id}/attachment", method = RequestMethod.GET, produces = "application/json")
    public FileDto getInvoiceAttachment(@PathVariable Long id) throws IOException {
        return service.getInvoiceAttachment(ContextService.getContext().getUserId(), id);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(path = "/invoice/add", method = RequestMethod.POST, produces = "application/json")
    public Long addInvoice(@RequestBody AddInvoiceDto addInvoiceDto) {
        return service.addInvoice(ContextService.getContext().getUserId(), addInvoiceDto);
    }

    @Secured(Constants.EMPLOYEE_ROLE)
    @RequestMapping(path = "/invoice/{id}/edit", method = RequestMethod.PUT, produces = "application/json")
    public void editInvoice(@PathVariable Long id, @RequestBody AddInvoiceDto addInvoiceDto) {
        service.editInvoice(ContextService.getContext().getUserId(), id, addInvoiceDto);
    }
}
