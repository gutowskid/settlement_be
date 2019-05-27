package pl.edu.pw.mini.payroll.rest.invoice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.mini.model.invoice.InvoiceStatus;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByStatus(InvoiceStatus status, Pageable pageable);
}
