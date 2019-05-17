package pl.edu.pw.mini.employee.rest.hours.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HoursRepository extends JpaRepository<Hours, HoursPK> {
    List<Hours> findByEmployeeIdAndDayIsAfterAndDayIsBefore(String employeeId, LocalDate after, LocalDate before);
}
