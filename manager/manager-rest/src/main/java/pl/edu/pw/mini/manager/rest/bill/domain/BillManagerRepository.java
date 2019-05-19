package pl.edu.pw.mini.manager.rest.bill.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.mini.model.bill.BillStatus;

import java.util.List;

public interface BillManagerRepository extends JpaRepository<BillManager, Long> {
    List<BillManager> findByManagers_managerIdContainsAndStatus(String managerId, BillStatus status);
}
