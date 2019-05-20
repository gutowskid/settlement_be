package pl.edu.pw.mini.payroll.rest.bill.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.mini.model.bill.BillStatus;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByStatus(BillStatus status);
}
