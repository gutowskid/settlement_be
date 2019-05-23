package pl.edu.pw.mini.employee.rest.invoice.domain;

import pl.edu.pw.mini.core.model.invoice.BaseInvoice;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE")
public class Invoice extends BaseInvoice {
}
