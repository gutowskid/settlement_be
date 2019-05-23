package pl.edu.pw.mini.employee.rest.invoice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByEmployeeIdAndArchived(String employeeId, Boolean archived, Pageable pageable);
}
