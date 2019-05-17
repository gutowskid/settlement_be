package pl.edu.pw.mini.employee.rest.hours.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.employee.rest.hours.domain.Hours;
import pl.gutowskid.employee.api.dayhour.HoursDto;

@Component
public class HoursDtoAssembler extends DtoAssembler<Hours,HoursDto> {

    @Override
    public HoursDto toDto(Hours input) {
        HoursDto dto = new HoursDto();
        dto.setDay(input.getDay());
        dto.setTask(input.getTask());
        dto.setCount(input.getCount());
        return dto;
    }
}
