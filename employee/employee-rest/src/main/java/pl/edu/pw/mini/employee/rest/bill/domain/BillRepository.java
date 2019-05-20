package pl.edu.pw.mini.employee.rest.bill.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Page<Bill> findByEmployeeIdAndProcessed(String employeeId, Boolean processed, Pageable pageable);
}
