package pl.edu.pw.mini.manager.rest.employeemanager.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeManagerRepository extends JpaRepository<EmployeeManager, EmployeeManagerPk> {
    Long countByManagerIdAndUser_isActive(String managerId, Boolean isActive);
}
