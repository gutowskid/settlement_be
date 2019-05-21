package pl.edu.pw.mini.manager.rest.bill.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.pw.mini.model.bill.BillStatus;
import pl.gutowskid.manager.api.info.EmployeeInfoDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BillManagerRepository extends JpaRepository<BillManager, Long> {
    Page<BillManager> findByManagers_managerIdContainsAndStatus(String managerId, BillStatus status, Pageable pageable);
    Long countByManagers_managerIdContainsAndStatus(String managerId, BillStatus status);

    @Query("SELECT NEW pl.gutowskid.manager.api.info.EmployeeInfoDto(bm.employeeId, SUM(bm.hours), SUM(bm.brutto)) FROM BillManager bm JOIN EmployeeManager em ON em.employeeId = bm.employeeId WHERE em.managerId = :managerId AND bm.status = :status AND bm.updateDate >= :after AND bm.updateDate < :before GROUP BY bm.employeeId")
    List<EmployeeInfoDto> getEmployeeInfo(@Param("managerId") String managerId, @Param("status") BillStatus status, @Param("after") LocalDateTime after, @Param("before") LocalDateTime before);
}
