package pl.edu.pw.mini.employee.rest.accountnumber.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, String> {
}
