package pl.edu.pw.mini.payroll.rest.bill.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.mini.model.bill.BillStatus;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Page<Bill> findByStatus(BillStatus status, Pageable pageable);
}
