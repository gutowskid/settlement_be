package pl.edu.pw.mini.employee.rest.bill.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByEmployeeIdAndProcessed(String employeeId, Boolean processed);
}
