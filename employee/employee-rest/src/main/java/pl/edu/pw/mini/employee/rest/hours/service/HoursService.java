package pl.edu.pw.mini.employee.rest.hours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.employee.rest.hours.domain.Hours;
import pl.edu.pw.mini.employee.rest.hours.domain.HoursPK;
import pl.edu.pw.mini.employee.rest.hours.domain.HoursRepository;
import pl.edu.pw.mini.model.Period;
import pl.gutowskid.employee.api.hours.HoursDto;

import java.util.List;

@Service
public class HoursService {

    @Autowired
    private HoursRepository repository;

    @Autowired
    private HoursDtoAssembler assembler;

    public void setDayHour(String employeeId, HoursDto dto) {
        Hours hours = repository.findById(HoursPK.builder().employeeId(employeeId).day(dto.getDay()).build()).orElse(new Hours());
        hours.setEmployeeId(employeeId);
        hours.setDay(dto.getDay());
        hours.setTask(dto.getTask());
        hours.setCount(dto.getCount());
        repository.save(hours);
    }

    public List<HoursDto> getMyDayHours(String employeeId, Period period) {
        List<Hours> list = repository.findByEmployeeIdAndDayIsAfterAndDayIsBefore(employeeId, period.getFrom(), period.getTo());
        return assembler.toDtoList(list);
    }
}
