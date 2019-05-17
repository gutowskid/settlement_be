package pl.edu.pw.mini.employee.rest.hours.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(HoursPK.class)
@Table(name = "HOURS")
public class Hours {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Id
    @Column(name = "DAY")
    private LocalDate day;

    @Column(name = "TASK")
    private String task;

    @Column(name = "COUNT")
    private Long count;
}
