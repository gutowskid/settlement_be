package pl.edu.pw.mini.manager.rest.bill.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.mini.model.bill.BillStatus;

public interface BillManagerRepository extends JpaRepository<BillManager, Long> {
    Page<BillManager> findByManagers_managerIdContainsAndStatus(String managerId, BillStatus status, Pageable pageable);
    Long countByManagers_managerIdContainsAndStatus(String managerId, BillStatus status);
}
