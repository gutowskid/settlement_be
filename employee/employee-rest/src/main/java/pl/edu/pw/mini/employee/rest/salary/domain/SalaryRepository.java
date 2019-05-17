package pl.edu.pw.mini.employee.rest.salary.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, String> {
}
