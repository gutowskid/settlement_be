package pl.edu.pw.mini.manager.rest.invoice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.mini.model.invoice.InvoiceStatus;

public interface InvoiceManagerRepository extends JpaRepository<InvoiceManager, Long> {
    Page<InvoiceManager> findByManagers_managerIdContainsAndStatus(String managerId, InvoiceStatus status, Pageable pageable);
    Page<InvoiceManager> findByManagers_managerIdContainsAndStatusIsNot(String managerId, InvoiceStatus status, Pageable pageable);
}
